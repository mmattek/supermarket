supermarket
===========
running: this project requires maven (http://maven.apache.org/)
also, I just used maven 3.1 and java 7

however, the spring boot plugin for maven can build an executable jar
java -jar techTest-0.0.1-SNAPSHOT.jar

apologies for putting this big jar in the repo, but if you didn't have
maven handy already, though this would help. Obviously, the curl commands
below would be the only test then of the code, ignore the basicClient.

C:\Users\mitch\git\supermarket2>mvn -version
Apache Maven 3.1.0 (893ca28a1da9d5f51ac03827af98bb730128f9f2; 2013-06-27 21:15:3
2-0500)
Maven home: C:\apache-maven-3.1.0
Java version: 1.7.0_25, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.7.0_25\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"

check out from git, terminal at directory
mvn spring-boot:run (in windows, you can precede with "start" to open in a new term window.

The bootstraps a spring boot container with a REST api. First launch will have to download
a bunch of dependencies into local maven repo and is a little slow.

While you can interact with the REST api with CURL -- really cygwin is required, or on mac/linx.
I included a java client to register the 3 pricers and call the /checkout endpoint.
execute this from the root directory: We override the spring boot start class
mvn exec:java  -Dexec.classpathScope=compile -Dstart-class="techTest.client.BasicClient"

if you want, you can interact with curl without using the client

curl -i -X POST -H "Content-Type: application/json" -d '{"letter":"A","pricePerUnit":"20"}' http://localhost:8080/registerStandardPricer

registers a pricer for "A" -- you'll see action in the other term running the spring boot app.

curl -i -X POST -H "Content-Type: application/json" -d '{"letter":"C","pricePerUnit":"30"}' http://localhost:8080/registerStandardPricer

now reg bulk pricer:
curl -i -X POST -H "Content-Type: application/json" -d '{"letter":"B","pricePerUnit":"50","discountQuantity":"5","discountPrice":"150"}' http://localhost:8080/registerBulkPricer

and checkout!
$ curl -i -X POST -H "Content-Type: application/json" -d 'ABBACBBAB' http://localhost:8080/checkout
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    12    0     3  100     9     63    191 --:--:-- --:--:-- --:--:--   191HTTP/1.1 200 OK
Date: Mon, 22 Dec 2014 02:22:06 GMT
X-Application-Context: application
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Server: Jetty(9.2.4.v20141103)

240

you can register additional pricers via the rest api curl above.

like an E for $120:
curl -i -X POST -H "Content-Type: application/json" -d '{"letter":"E","pricePerUnit":"120"}' http://localhost:8080/registerStandardPricer

and add to order and checkout:
$ curl -i -X POST -H "Content-Type: application/json" -d 'ABBACBBABE' http://localhost:8080/checkout
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    13    0     3  100    10     93    312 --:--:-- --:--:-- --:--:--   312HTTP/1.1 200 OK
Date: Mon, 22 Dec 2014 02:29:46 GMT
X-Application-Context: application
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Server: Jetty(9.2.4.v20141103)

360




design notes:

I decided to add the REST spring boot api because I've been using it a little on a new project
and think it is super easy to use. Adding the service was a couple hours work, and some of that
was my inexperience with the auto-binding library for POJOs.

As for object design, I elected for an abstract class over interface, as much of the logic is shared.
The bulkpricer is really an extension class, with a small tweak, but still needs all the base functionality.
I decided to pass the entire order to the pricer in order to decouple and future
proof it. You have to pass the number of items to the pricer, and its just as easy to pass the whole
order and extract the info from that. In general, I like passing the whole object to a method in another
object rather than an attribute of that object. It promotes abstraction and encapsulation.
Here, we could implement a new Pricer without changing 
the contract of price(String order). For instance, a "Buy an A, get a B for $10 off package deal". 
The pricer would already have the entire order context, and could easily deal with that.

Nothing in here is really stateful, so you could scale this out by having many markets.
In a real project for production, I would probably use an external config/data store to have precanned
items loaded on startup.  I'm also unhappy with having to add in no-arg constructors 
to the object model to get Spring's Jackson JSON binding to work. I know this is possible to override
with annotations. Also, since the service was a bit of an afterthought, there aren't really unit
tests for it, other than a small binding one. In reality, spring lets you sub in mocks pretty easy
and also you can do integration by launching an embedded container on a random avail. port and reference 
that. We are doing this on several "microservices" projects right now and it works well.

Also for scale, spring cloud netflix offers dynamic, load-balanced routing in Zuul, distributed config 
servers, etc. I'm looking into this for a project right now.

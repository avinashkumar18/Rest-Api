# Kaiburr-Assessment


# Task 1. JAVA Rest API Example:
I have created a sample JAVA Rest Api application using Spring Boot v2.6.6. All the required code are uploaded in this git repository: master branch.
Created Server Pojo with values id, name, language and framework. Also created Rest endpoints,
1.	@PostMapping("/addServer")    Add new server
2.	@GetMapping("/servers")	 Viewing all servers
3.	@GetMapping("/servers/{id}")	 Find server by id
4.	@GetMapping("/server/{name}")	 Find server by name
5.	@DeleteMapping("/delete/{id}") Delete existing server by id 
For this project, I created spring boot application with web dependency. After creating pojo, a repository is needed to store all the values. For this MongoDB is chosen to store the values of the server.
To work with MongoDb, First we need to add its maven dependency,
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
After this, added few configurations like MongoDb connection uri, database name are configured in application.properties file.
ServerRepo interface is created which extends MongoRepository interface with an unimplemented method findByName as a part of requirement.
Created a collection “details” in a database “server”. This database is present in Mongodb shared cluster hosted by MongoDB itself in Mongodb Atlas platform.
Using the @Document annotation, mentioned the collection name in the pojo. Added @EnableMongoRepositories annotation to the main class of the project.
In the ServerController java file, all the logic of rest endpoints has been added. The methods are,
1.	public String saveServer(@RequestBody ServerDto dto)
2.	public List<ServerDto> getAllServers()
3.	public Optional<ServerDto> getServerById(@PathVariable String id)
4.	public List<ServerDto> getServerByName(@PathVariable String name)
5.	public String DeleteServerById(@PathVariable String id)

All the above endpoints are published into cloud using Heroku platform. Below are the url and the sereenshot of each endpoint.
View All Servers:
https://kaiburr-assessment.herokuapp.com/servers
![image](https://user-images.githubusercontent.com/31410414/163729911-c99358cb-440c-46d9-8662-aca1436c0d26.png)

 

Find server by id
https://kaiburr-assessment.herokuapp.com/servers/625afd2ee1142879597471e2

 ![image](https://user-images.githubusercontent.com/31410414/163729927-e15a7bd4-f2d7-4fe6-b991-b52792c79990.png)


Find server by name
https://kaiburr-assessment.herokuapp.com/server/centos

 ![image](https://user-images.githubusercontent.com/31410414/163729934-cc33d189-7424-40ce-ba04-1eb011e54535.png)


Add new server
https://kaiburr-assessment.herokuapp.com/addServer
Body:
{
  "name": "Ubuntu",
  "language": "Java",
  "framework": "Hibernate"
}
  ![image](https://user-images.githubusercontent.com/31410414/163729940-7469f40a-0083-41ec-b0ea-9e1325f1d514.png)

 
Delete existing server
https://kaiburr-assessment.herokuapp.com/delete/625c59da06d65c37b2470070

 ![image](https://user-images.githubusercontent.com/31410414/163729944-a04e1b0f-889d-40f1-b31c-e915d95bdb3e.png)


# Task 2. Swagger Codegen:
I have tried to add the swagger for the above application by adding the following maven dependency,
<!-- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui -->
		<dependency>
    			<groupId>org.springdoc</groupId>
    			<artifactId>springdoc-openapi-ui</artifactId>
    			<version>1.6.7</version>
		</dependency>
I made this swagger exposed in different port instead of port running the application. To make this possible, I added actuator maven dependency and few configuration in application.properties file,
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator -->
		<dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-actuator</artifactId>
    		</dependency> 
springdoc.use-management-port=true
management.server.port=8090
management.endpoints.web.exposure.include=openapi, swaggerui
Now, api-docs and swaggerui are exposed on the port 8090.

{
    "openapi": "3.0.1",
    "info": {
        "title": "OpenAPI definition",
        "version": "v0"
    },
    "servers": [
        {
            "url": "http://localhost:8080",
            "description": "Generated server url"
        }
    ],
    "paths": {
        "/addServer": {
            "post": {
                "tags": [
                    "server-controller"
                ],
                "operationId": "saveServer",
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ServerDto"
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "string"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/servers": {
            "get": {
                "tags": [
                    "server-controller"
                ],
                "operationId": "getAllServers",
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "array",
                                    "items": {
                                        "$ref": "#/components/schemas/ServerDto"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        "/servers/{id}": {
            "get": {
                "tags": [
                    "server-controller"
                ],
                "operationId": "getServerById",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "string"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "$ref": "#/components/schemas/ServerDto"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/server/{name}": {
            "get": {
                "tags": [
                    "server-controller"
                ],
                "operationId": "getServerByName",
                "parameters": [
                    {
                        "name": "name",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "string"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "array",
                                    "items": {
                                        "$ref": "#/components/schemas/ServerDto"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        "/hello": {
            "get": {
                "tags": [
                    "server-controller"
                ],
                "operationId": "name",
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "string"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/delete/{id}": {
            "delete": {
                "tags": [
                    "server-controller"
                ],
                "operationId": "DeleteServerById",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "string"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "string"
                                }
                            }
                        }
                    }
                }
            }
        }
    },
    "components": {
        "schemas": {
            "ServerDto": {
                "type": "object",
                "properties": {
                    "id": {
                        "type": "string"
                    },
                    "name": {
                        "type": "string"
                    },
                    "language": {
                        "type": "string"
                    },
                    "framework": {
                        "type": "string"
                    }
                }
            }
        }
    }
}
![image](https://user-images.githubusercontent.com/31410414/163729958-c0a91f26-fc24-434b-9183-ccaed470188c.png)
![image](https://user-images.githubusercontent.com/31410414/163729963-98498f24-fc54-4d4b-9fc4-a29580852d36.png)

 
 

In this interactive swaggerui we can test all the endpoints as it provides all the functionalities to document and test APIs
 ![image](https://user-images.githubusercontent.com/31410414/163729970-abab03e1-84c0-46e5-8cf5-a6393fa3850a.png)
![image](https://user-images.githubusercontent.com/31410414/163729972-4af9abfc-d0b0-4827-8592-fcb614acc1a6.png)
![image](https://user-images.githubusercontent.com/31410414/163729974-ed2a4ca2-da65-48c5-9735-368a8da9c9f7.png)


 

 

# Task 3. Dockerfiles:
For this task, I used the first application to create a docker image into https://hub.docker.com  I added the Jib plugin into my project,
Jib is an interesting plugin developed by Google. https://cloud.google.com/java/getting-started/jib This plugin will help to create docker image directly into the docker hub without any Dockerfile
	
		<plugin>
      			<groupId>com.google.cloud.tools</groupId>
        		<artifactId>jib-maven-plugin</artifactId>
        		<version>2.8.0</version>
      		</plugin>
I was able to create docker image locally and then I pushed the image to the Docker hub
	
	mvn compile jib:dockerBuild

![image](https://user-images.githubusercontent.com/31410414/165156741-3f21ea28-76e4-4a6e-a07c-b18c07d908ee.png)
![image](https://user-images.githubusercontent.com/31410414/165256790-a4b54044-a7a7-49a0-a93f-76844b100578.png)



# Task 4. Web UI Forms:
I used the first application to create web UI. The framework I chose to create web UI was Vaadin. Vaadin is a application platform for java. We just need to add its maven dependency which will install all the node modules in the runtime.
Two classes are created, MainView.java and ServerEditor. MainView file deals with all the UI parts that are needed for the application. ServerEditor file calls the controller for all the logics in the backend.
Altogether, the frontend UI is developed using vaadin as a platform.
 
To View all the servers 
  ![image](https://user-images.githubusercontent.com/31410414/163729984-f2833628-fef9-4d96-8711-c5fdf86b1096.png)

To add new Server “New Server” Button is clicked
 ![image](https://user-images.githubusercontent.com/31410414/163729987-9358c579-49c2-4c06-b057-22ddf7e34006.png)

 
New server details are filled in the respective boxes
 
![image](https://user-images.githubusercontent.com/31410414/163729988-00f13b94-638a-4435-bc8f-e2d360e41565.png)

New server is added to the list
 
![image](https://user-images.githubusercontent.com/31410414/163729994-38db9463-3ad5-4af7-9794-c56601d511ed.png)



Finding server by name
 
![image](https://user-images.githubusercontent.com/31410414/163729996-cda5fc4c-6966-4aed-a1b0-93ab0a98fe4d.png)

# Task 5. CI-CD pipeline:
I used Jenkins to build and publish docker image into the registry. I downloaded all the necessary plugins in Jenkins to configure the docker build. The configurations were setup correctly in Jenkins and the code is cloned from this github repository.
The application building part was successful while the docker image build was a failure. Due to some unknown exceptions, my docker build failed and was not able to create image.
 ![image](https://user-images.githubusercontent.com/31410414/163729998-95c92cf6-0f5b-4b7a-9b59-923be3a0c55d.png)
	#UPDATE
	
	I was able to build Docker image uploaded in the docker hub via Jenkins
![image](https://user-images.githubusercontent.com/31410414/165256444-598c0b57-a509-4aa2-9e7b-47bccb8e8f01.png)
![image](https://user-images.githubusercontent.com/31410414/165256487-94f34512-fecb-475b-8d45-a334a356619f.png)
	


# Task 6. Machine Learning Program
The Machine Learning program is present in the branch “MachineLearning”. I imported brest_cancer dataset for the implementation. Did some preprocessing of data by separating target variable and viewing target classes ['malignant', 'benign'] and then finding the class counts.
Used sklearn.model_selection package for splitting train and test data. The data is converted into binarized format then applied logistic regression since its 2 class problem





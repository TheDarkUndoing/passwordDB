# passwordDB
Currently a database builder for the Collection password leak
Eventually going to be a full web application

## Build Instructions
Instructions for building and deployment as well as necessary requirements

###Build Project
From Java/passwordDB/
'''
gradle build
'''
###Deploying Project
Take jar from build/libs/ and place in same folder with testData.tar.gz
'''
java -jar passwordDB.jar testData.tar.gz
'''

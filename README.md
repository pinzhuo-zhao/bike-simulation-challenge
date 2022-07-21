## Bike-Simulation Coding Challenge
### Details on how to run and test the project


<pre>
git clone https://github.com/pinzhuo-zhao/bike-simulation-challenge.git

cd bike-simulation-challenge

#To run unit test for this project
mvc test

#To run the project
mvc package

cd target

java -jar bike-simulation-challenge-1.0-SNAPSHOT.jar ../sample.txt

# Or You can use your own command input file
java -jar bike-simulation-challenge-1.0-SNAPSHOT.jar <\Your_File>
</pre>

### Why I have chosen this coding challenge
I chose the first challenge because it seems more like a project that could demonstrate OOP thinking, as components in the bike simulation are more conspicuous than the phonewords challenge, such as Bike, Grid, Direction, etc. Also, the phonewords challenge appears to me as an algorithm design challenge more than an OOP design challenge.

### My Design & Approach 
#### Entity Classes
The first thing I did is to list out each possible entity after reading the challenge description, including Grid, Bike, Direction, Position and Command.  Since Bike is the main business logic class in this project, I made a Bike interface and a default implementation for it, for the ease of future expansion and manipulation (Open-closed principle), the Simulator (will be introduced later) class also depend on the Bike interface instead of an implementation class to reduce the coupling between them. Command and Direction were designed as Enum, for better maintainability and readability, and it will be less likely for me to make mistakes when I need to reference those commands and directions.

#### Util Classes
I designed a Result class for standardizing the return value after executing a command, an result object contains the information of success status and returned data. A properties file was created for easier management of some values, like the grid size, therefore a PropertiesUtils was designed for packaging the methods of interacting with the properties file. Also, as the program will read an external text file as command input, so I made a FileParsingUtils for packaging the methods of reading/writing/validating a file.

#### Simulator Class
And there is a separate Simulator class for handling each direct command read from the text file, instead of integrating this process with any of the aforementioned entity classes, creating a separate Simulator class enhanced the cohesion, and that is one of the principles of OOP thinking.


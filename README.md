# ResonanceDB
A project which parses a large dataset from Last.FM, and also pulls additional information from the Last.FM API, stores this information into an SQL database, and presents the user with a web interface which allows them to discover new music based on music they already like, by inputting a song, an artist, or a descriptive tag and finding related songs or artists. 

All backend code is Java. Database used is MySQL hosted on Amazon AWS using Relational Database Service (RDS). Web interface created using PHP.

The Final report details the division of tasks for this group project. The only part I had no direct role in writing was the PHP web interface, while I had indirect roles in debugging PHP code and hosting a HTTP endpoint which acted as a middleman between the PHP web interface and the Last.FM API, allowing the PHP interface to make very simple API requests and recieve a cleansed JSON response.

**Private investigator**

This project groups together similar sentences (sentences where only a single word has
changed) and extracts the changes.

**To run the project**
1) clone the project

git clone https://github.com/mohsenoffice/investigator.git

2) mvn clean install

3) java -cp target/ServiceNow-1.0-SNAPSHOT.jar org.servicenow.App


**Assumptions:**

1) All Lines starts with a timestamp that (in the format "date time") - otherwise it will be ignored
2) Similar sentences have the same number of words
3) The comparison is case sensitive
4) Only extra spaces are ignored. e.g  "a &nbsp;&nbsp;&nbsp; b &nbsp;&nbsp;&nbsp; "  AND "a b" are the same sentence (Special characters are not ignored!).
5) All the text file can be loaded into the memory
6) Use MD5 to generate Unique ID for a string (Assume it's unique enough)

**Time complexity:**

Max sentens length (in words) -> M

Number of lines in the file  -> N

Parsing each line we create maximum M sentences (the different between 2 sentences is exactly one word)

We go over the N Line and for each line, we create M sentences [see image](https://github.com/mohsenoffice/investigator/blob/master/Screenshot_4.jpg) -> worst-case  O(M*N)

To find similar sentence we go over M permutations of the line and check if it's existing in the hash (see how in the description file) -> O(M)

So the time complexity is O(M*N)


**If I have one more week:**

1) Look for a better algorithm (to reduce memory consumption)
2) Add concurrency to the process (e.g split the file into K files, then parse each file independently(in a separate thread),  then merge the files.
3) Write logs and errors into a log file.
4) Add more unit tests
5) Add GUI
6) create a more efficient function for a unique value generator.

**TO DO better:**
1) Use Spring framework (MVC or Spring-boot)
2) Use diterputed computing - it heps in scale

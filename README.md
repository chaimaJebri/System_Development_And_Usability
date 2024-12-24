# Overview
The purpose of this project is to evaluate Nielsen’s usability heuristics to see how they impact the overall user experience. This was done by developing two versions of an online learning application: one that follows these heuristics and another that does not. A usability testing was then carried out using the System Usability Scale (SUS) questionnaire with two groups of five participants each. The first group engaged with the advanced version of the application, whereas the other interacted with the basic version. Subsequently, by conducting the Mann-Whitney statistical test on the SUS scores obtained, it was found that the advanced version of the application was perceived as more usable than the basic version, demonstrating the relevance of Nielsen’s usability heuristics to the design process of modern user-friendly applications.

# Project Structure
* OnlineLearningApplication: The app adhering to Nielsen's Usability Heuristics.
* OnlineLearningApp2: The app that does not adhere to Nielsen's Usability Heuristics.
* Results.pdf: the file that includes detailed results of the usability testing and statistical analysis.

Both applications provide the same core functionality but differ in their usability, aiming to determine whether the set of heuristics effectively enhances system usability.

# About the App
The application is designed to provide users with an interactive and engaging way to learn programming.

Users can:
* Create an account and log in
* Edit profile details (name, username, email address, password, etc)
* Browse available courses and explore modules within each course.
* Take quizzes and enjoy a set of random coding jokes when achieving a good score.
* Receive and send notifications through the app.

# Development Tools & Technologies
* Java 17
* Apache Tomcat v9.0
* MySQL Database
* Eclipse IDE
* JavaScript / jQuery
* HTML / CSS
* JDBC
* Bootstrap 5

# Usability Testing and Analysis Tools
* Mann-Whitney Test
* SPSS
* System Usability Scale (SUS) questionnaire

# Usage
Both versions of the online learning application can be set up and run in Eclipse using the same process.
* Clone the repository to your local machine.
* Import the project into Eclipse IDE
	- Open Eclipse and go to File > Import > Existing Projects into Workspace
	- Navigate to the cloned repository folder and select the OnlineLearningApplication or OnlineLearningApp2 folder, depending on which version you want to run.
	- Click Finish
* Configure MySQL database.
* Run the project (Right click on the project – Run as – Run on server).

If you are using different versions of Java or Tomcat, you may need to configure a Build Path and a Target Runtimes (Right click on the project - Properties – Java Build Path / Target Runtimes).


Author: Chaima Jebri

Last updated: December 24, 2024

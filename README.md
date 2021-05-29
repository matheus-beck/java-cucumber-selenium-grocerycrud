# 💻 QA Java, Cucumber and Selenium Challenge

<div align="center">
  <img alt="ibm logo" width="20%" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/IBM_logo.svg/1000px-IBM_logo.svg.png">
</div>
<br>

Test Automation of [this web page](https://www.grocerycrud.com/demo/bootstrap_theme) using Java, Cucumber, and Selenium for the tech QA challenge from [IBM](https://www.ibm.com/). 

The features of the tests are inside `src/test/resources/prova_sicredi/grocerycrud.feature` and the step definitions are inside `src/test/java/prova_sicredi/StepDefinitions.java`

## 📅 Test execution report
![Cucumber report](cucumber_report.png)
- [Link to test execution report](Test_Results_Feature__grocerycrud.html)

## 💾 Development Dependencies Used

- "Apahce Maven": "3.6.3"
- "Java":  " 11.0.9.1"
- "Selenium": "3.141.59"
- "Cucumber": "6.10.4"
- "Chrome Driver": "90.0.4430.24"
- "Google Chrome": "90.0.4430.212"

## 🎬 Getting Started

1. Clone the project into your machine, install all dependencies described above and compile the project using:

```console
mvn install
```

2. Now, to run the tests:

```console
mvn test
```

3. Verify the tests results output:

![Test results output](test_results.png)

---

Made with ❤️ by Matheus Beck 👋 [Get in touch!](https://www.linkedin.com/in/matheus-beck/)
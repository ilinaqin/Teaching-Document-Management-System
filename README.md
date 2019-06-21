# Teaching-Document-Management-System
This system is mainly for the convenience of college teachers and leaders to manage documents</br>
The framework used in this system is SSH. </br>
&nbsp;&nbsp;&nbsp;&nbsp;The system is mainly aimed at teachers and leaders of colleges and universities. It provides users with functions such as uploading, deleting, modifying, browsing, downloading, searching, and auditing teaching documents, classifying them according to document types, and finally archiving them.</br> 
&nbsp;&nbsp;&nbsp;&nbsp;The structure of the project is divided into presentation layer, business layer and data access layer. The dependencies between the layers are from bottom to top. The technologies used are Struts, Spring, ibernate, Log4J and so on. The presentation layer is developed using the Struts framework; the data access layer is implemented by means of Hibernate; the transaction part utilizes Spring's declarative transaction management.
</br> </br> 
result show:</br> </br> 
1、This is a login page, and the teachers and teachers will log in to their account according to their respective authorities.
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/1.jpg)</br> </br> 
2、Document upload page, upload the corresponding document according to your needs</br> 
upload :</br> 
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/2.jpg)</br>
upload success!</br> 
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/3.jpg)</br> </br> 
3、Preview or download uploaded documents</br> 
preview:</br> 
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/4.jpg)</br>
download:</br> 
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/5.jpg)</br> </br> 
4、The administrator or the secretary gives the corresponding audit result by viewing the document. </br> 
   If the audit fails, the reason for the return is given.</br> 
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/6.jpg)</br> </br> 
5、Store the approved documents</br> 
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/7.jpg)</br> </br> 
6、Database Design</br> 
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/8.jpg)
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/9.jpg)</br> </br> 
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/10.jpg)</br> </br> 
7、Navigation bar</br> 
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/11.jpg)
![image](https://github.com/ilinaqin/Teaching-Document-Management-System/blob/master/img_display/12.jpg)</br> 
</br> </br> 
steps:</br> 
First run login.jsp</br> 
Then if tomcat starts incorrectly, turn it off and restart</br> 
Last, run "http://localhost:8080/QLN/login.action" in a browser</br> 





INSERT INTO `band` VALUES (1,'test band name',9),(2,'Apprentice',8),(3,'Trainee',7),(4,'Associate',6),(5,'Senior Associate',5),(6,'Consultant',4),(7,'Manager',3),(8,'Principal',2),(9,'Leadership Community',1),(10,'Executive',0);

INSERT INTO `training` VALUES (2,'Effective Time Management','Professional Skills','Identify what effective time management looks like\n','https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Time-Management.aspx'),(3,'Some Technical Training','Technical Skills','Learn more about how to improve your tehcnical skills','https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Time-Management.aspx'),(4,'Some Development Training','Development Programmes','Identify the benefits on developing new skills','https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Time-Management.aspx');

INSERT INTO `band_training` VALUES (2,1,2),(3,1,3),(4,2,3),(6,6,3),(7,4,4);

INSERT INTO `capability` VALUES (1,'test cap name'),(2,'Applied Innovation'),(3,'Business Development'),(4,'Product'),(5,'Cyber Security'),(6,'Data & Analytics'),(7,'Engineering'),(8,'Delivery Management'),(9,'Platforms');

INSERT INTO `competency_type` VALUES (1,'Commercial Awareness'),(2,'Planning and Organising'),(3,'Innovation and Continuous Inprovement'),(4,'Job Specific Knowledge'),(5,'Developing Yourself and Others'),(6,'Communicating & Teamwork'),(7,'Customer Focus');

INSERT INTO `responsibility` VALUES (1,'test responsibility name'),(2,'developing high quality soluti');

INSERT INTO `competency` VALUES (1,1,2,'Placeholder description for App. C.A'),(2,2,2,'Placeholder description for App. P&O'),(3,3,2,'Placeholder description for App. I&C.I'),(4,4,2,'Placeholder description for App. J.S.K'),(5,5,2,'Placeholder description for App. D.Y&O'),(6,6,2,'Placeholder description for App. C&T'),(7,7,2,'Placeholder description for App. C.F'),(8,7,3,'Placeholder description for Train. C.F'),(9,6,3,'Placeholder description for Train. C&T');

INSERT INTO `job_family` VALUES (1,'Corporate Security',5),(2,'Engineering Strategy and Planning',7),(3,'Engineering',7),(4,'Architecture',7),(5,'Testing and Quality Assurance',7),(6,'Product Specialist',7),(7,'Security Strategy and Planning',5),(9,'Test Job family',1),(10,'Platform Engineering',2),(11,'Product Consultancy',4),(12,'Testing and Quality Assurance',5),(13,'Digital Advisory Consultancy',4),(14,'Specialist Consultancy',4);

INSERT INTO `employee_role` VALUES (1,'test role','test spec','google.com',1,9),(2,'Test Engineer','As a Test Engineer (Associate) in Kainos, you’ll be responsible for good quality of the software',NULL,4,12),(5,'Software Engineer','As a Software Engineer in Kainos, you’ll be responsible for\n developing high quality solutions which delight our customers and impact the lives of users worldwide.',NULL,3,3),(6,'Software Engineer','As a Software Engineer in Kainos, you’ll be responsible for\n developing high quality solutions which delight our customers and impact the lives of users worldwide.','https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&originalPath=aHR0cHM6Ly9rYWlub3Nzb2Z0d2FyZWx0ZC5zaGFyZXBvaW50LmNvbS86YjovZy9wZW9wbGUvRVlUQ3Yxc3NsNnBPdUg1OXpYdG9GOVlCOHFOYUVNTlNrWklrQ3RoREFZNUtqZz9ydGltZT1sWEpNc29SbzJVZw',4,7),(7,'Product Specialist','As a Product Specialist at Kainos you will be responsible for delivering high quality solutions which delight our customers and\nimpact the lives of users worldwide.',NULL,8,11),(8,'Digital Advisory Consultant','Work with delivery managers and solution architects to shape the approach for the work you and the wider team undertakes',NULL,8,13),(9,'Specialist Consultant','Work with delivery managers and solution architects to shape the approach for the work you and the wider team undertakes',NULL,8,14),(10,'Digital Advisory Consultant','Work with delivery managers and solution architects to shape the approach for the work you and the wider team undertakes',NULL,7,13),(11,'Specialist Consultant','Work with delivery managers and solution architects to shape the approach for the work you and the wider team undertakes',NULL,7,14);

INSERT INTO `responsibility_employee_role` VALUES (1,1,1),(2,2,2),(3,1,2);






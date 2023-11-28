1) Swagger URL :
   http://localhost:8081/swagger-ui/index.html#/
------------\n
2) Table :
   ALTER TABLE mbp.user_School_T ADD user_nameOfTeam varchar(50) not NULL;
   commit;
4) 
   SELECT * FROM mbp.users;
   SELECT * FROM mbp.user_School_T;
   SELECT * FROM mbp.token;
   SELECT * FROM mbp.COUNTRY_MASTER;
   SELECT * FROM mbp.states_master;
   SELECT * FROM mbp.cities_master;
   select * from mbp.MBPTeam;

   SELECT * FROM mbp.school;
   SELECT * FROM mbp.school_poc;
   SELECT * FROM mbp.school_meetings;
   SELECT * FROM mbp.training;  
   SELECT * FROM mbp.MBPFlags;
   SELECT * FROM mbp.agreement;
   SELECT * FROM mbp.school_grades;
   select * from mbp.SchoolBoard;

 ------------\n
3) Static data inserted in tables :

Insert Into mbp.COUNTRY_MASTER Values(1,'+91','India');
Insert Into mbp.MBPTeam Values(1,'Central_Mool');
Insert Into mbp.MBPTeam Values(2,'OutReach_Head');
Insert Into mbp.MBPTeam Values(3,'OutReach');
Insert Into mbp.MBPTeam Values(4,'TrainTheTrainer_Head');
Insert Into mbp.SchoolBoard Values(1,'CBSE');
Insert Into mbp.SchoolBoard Values(2,'ICSE');
Insert Into mbp.SchoolBoard Values(3,'IB');
Insert Into mbp.SchoolBoard Values(4,'NIOS');
Insert Into mbp.SchoolBoard Values(5,'AISSCE');
Insert Into mbp.SchoolBoard Values(6,'SSC');
Insert Into mbp.TargetPhase Values(1,'MiniPilot');
Insert Into mbp.TargetPhase Values(2,'Pilot');
commit;


------------\n


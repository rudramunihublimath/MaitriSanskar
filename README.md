1) Swagger URL :
   http://localhost:8081/swagger-ui/index.html#/
------------\n
2) Table :
   SELECT * FROM mbp.users;
   SELECT * FROM mbp.token;
   SELECT * FROM mbp.COUNTRY_MASTER;
   SELECT * FROM mbp.states_master;
   SELECT * FROM mbp.cities_master;
   select * from mbp.MBPTeam;

   SELECT * FROM mbp.school;
   SELECT * FROM mbp.school_poc;
   SELECT * FROM mbp.school_meetings;
   SELECT * FROM mbp.outreach;
   SELECT * FROM mbp.training;
   SELECT * FROM mbp.MBPFlags;
   select * from mbp.SchoolBoard;

 ------------\n
3) Static data inserted in tables :

Insert Into COUNTRY_MASTER Values(1,'+91','India');
Insert Into STATES_MASTER Values(1,1,'Punjab');
Insert Into STATES_MASTER Values(2,1,'Karnataka');
Insert Into STATES_MASTER Values(3,1,'Maharashtra');
Insert Into CITIES_MASTER Values(1,'Ludhiana',1);
Insert Into CITIES_MASTER Values(2,'Amritsar',1);
Insert Into CITIES_MASTER Values(3,'Banglore',2);
Insert Into CITIES_MASTER Values(4,'Mysore',2);
Insert Into CITIES_MASTER Values(5,'Mumbai',3);
Insert Into CITIES_MASTER Values(6,'Pune',3);
commit;

MBP Teams :
Insert Into mbp.MBPTeam Values(1,'OutReach');
Insert Into mbp.MBPTeam Values(2,'OutReach_Head');
Insert Into mbp.MBPTeam Values(3,'TrainTheTrainer');
Insert Into mbp.MBPTeam Values(4,'TrainTheTrainer_Head');
Insert Into mbp.MBPTeam Values(5,'Central_Mool');

Insert Into mbp.SchoolBoard Values(1,'CBSE');
Insert Into mbp.SchoolBoard Values(2,'ICSE');
Insert Into mbp.SchoolBoard Values(3,'IB');
Insert Into mbp.SchoolBoard Values(4,'NIOS');
Insert Into mbp.SchoolBoard Values(5,'AISSCE');

Insert Into mbp.TargetPhase Values(1,'MiniPilot');
Insert Into mbp.TargetPhase Values(2,'Pilot');

------------\n


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

   SELECT * FROM mbp.school_01;
   SELECT count(*) FROM mbp.school_01;
   SELECT * FROM mbp.school_poc_02;
   SELECT * FROM mbp.school_meetings_03;
   SELECT * FROM mbp.training_04;  
   SELECT * FROM mbp.mbpflags_05;
   SELECT * FROM mbp.agreement_06;
   SELECT * FROM mbp.school_grades_07;
   select * from mbp.SchoolBoard;

 ------------\n
3) Static data inserted in tables :

Insert Into mbp.COUNTRY_MASTER Values(1,'+91','India');
Insert Into mbp.MBPTeam Values(1,'Central_Mool');
Insert Into mbp.MBPTeam Values(2,'OutReach_Head');
Insert Into mbp.MBPTeam Values(3,'OutReach');
Insert Into mbp.MBPTeam Values(4,'TrainTheTrainer_Head');

Insert Into mbp.TargetPhase Values(1,'MiniPilot');
Insert Into mbp.TargetPhase Values(2,'Pilot');

Insert Into mbp.SchoolBoard Values(1,'CBSE');
Insert Into mbp.SchoolBoard Values(2,'ICSE');
Insert Into mbp.SchoolBoard Values(3,'IB');
Insert Into mbp.SchoolBoard Values(4,'NIOS');
Insert Into mbp.SchoolBoard Values(5,'AISSCE');
Insert Into mbp.SchoolBoard Values(6,'SSC');
Insert Into mbp.SchoolBoard Values(	7	,'IPC ');
Insert Into mbp.SchoolBoard Values(	8	,'IBDP ');
Insert Into mbp.SchoolBoard Values(	9	,'KED ');
Insert Into mbp.SchoolBoard Values(	10	,'CAIE ');
Insert Into mbp.SchoolBoard Values(	11	,'PYP');
Insert Into mbp.SchoolBoard Values(	12	,'IGSCE');
Insert Into mbp.SchoolBoard Values(	13	,'HSC');
Insert Into mbp.SchoolBoard Values(	14	,'IGCSE');
Insert Into mbp.SchoolBoard Values(	15	,'CES');
Insert Into mbp.SchoolBoard Values(	16	,'ISC');
Insert Into mbp.SchoolBoard Values(	17	,'CISCE');
Insert Into mbp.SchoolBoard Values(	18	,'CIPP');
Insert Into mbp.SchoolBoard Values(	19	,'IEYC');
Insert Into mbp.SchoolBoard Values(	20	,'CIE');
Insert Into mbp.SchoolBoard Values(	21	,'IES');
Insert Into mbp.SchoolBoard Values(	22	,'Cambridge');
Insert Into mbp.SchoolBoard Values(	23	,'MYP');
Insert Into mbp.SchoolBoard Values(	24	,'CICSE');
Insert Into mbp.SchoolBoard Values(	25	,'EYP');
commit;

------------\n


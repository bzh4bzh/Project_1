CREATE TABLE employee(
    userid NUMBER PRIMARY KEY,
    fullname VARCHAR2(50),
    username VARCHAR2(50) UNIQUE,
    pass VARCHAR2(50) NOT NULL,
    reportsto NUMBER,
    department NUMBER,
    authority NUMBER, 
    remainingBal number,
    CONSTRAINT authority_check CHECK (authority>=0 AND authority<4)
);
create table request(
    requestID number primary key,
    userID number,
    eventname varchar2(50),
    eventlocation varchar2(200),
    eventdate date,
    eventdescription varchar2(200),
    eventtype number,
    gradingScale number,
    passingGrade varchar2(20),
    justification varchar2(200),
    eventcost number,
    reimbursment number,
    status number default 0, 
    finalgrade varchar2(20),
    moreinfo varchar2(1000),
    foreign key (userid) references employee(userid),
    constraint check_status check (status>=-1 and status<=4)
);
--alter table request drop constraint check_status;
--alter table request add constraint check_status check (status>=-1 and status<6);

drop sequence recseq;
create sequence recseq;

CREATE OR REPLACE TRIGGER recidmaker
BEFORE INSERT ON request
FOR EACH ROW
BEGIN
    SELECT recseq.NEXTVAL INTO: NEW.requestid FROM dual;
END;


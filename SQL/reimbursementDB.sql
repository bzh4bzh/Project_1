CREATE TABLE employee(
    userid NUMBER PRIMARY KEY,
    fullname VARCHAR2(50),
    username VARCHAR2(50) UNIQUE,
    pass VARCHAR2(50) NOT NULL,
    reportsto NUMBER,
    department NUMBER,
    authority NUMBER, 
    remainingBal number default 1000,
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
    foreign key (userid) references employee(userid),
    constraint check_status check (status>=-1 and status<4)
);

ALTER TABLE request
ADD (
  docs VARCHAR2(500)
);

ALTER TABLE request
MODIFY (
  flagged number default -1
);


create table title(
    titleid number primary key,
    titlename varchar2(20)
);

create table attachment(
    userid number,
    requestid number,
    docs varchar2(500)
);

drop table attachment;

create table department(
    deptid number,
    deptname varchar2(20)
);

create table eventType(
    eventid number primary key,
    eventtype varchar2(20),
    reimbursePercent number
);

create table gradingScale(
    gradeid number primary key,
    gradename varchar2(10)
);

create sequence recseq;

select recseq.currval from dual;

CREATE OR REPLACE TRIGGER recidmaker
BEFORE INSERT ON request
FOR EACH ROW
BEGIN
    SELECT recseq.NEXTVAL INTO: NEW.requestid FROM dual;
END;


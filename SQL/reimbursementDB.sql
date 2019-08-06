CREATE TABLE employee(
    userid NUMBER PRIMARY KEY,
    fullname VARCHAR2(50),
    username VARCHAR2(50) UNIQUE,
    pass VARCHAR2(50) NOT NULL,
    reportsto NUMBER,
    authority NUMBER, 
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
    status number, 
    foreign key (userid) references employee(userid),
    constraint check_status check (status>=-1 and status<4)
);

create table title(
    titleid number primary key,
    titlename varchar2(20)
);

create table attachment(
    userid number,
    doc blob
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

create sequence empseq;
create sequence recseq;

CREATE OR REPLACE TRIGGER useridmaker
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
    SELECT empseq.NEXTVAL INTO: NEW.userid FROM dual;
END;

CREATE OR REPLACE TRIGGER recidmaker
BEFORE INSERT ON request
FOR EACH ROW
BEGIN
    SELECT recseq.NEXTVAL INTO: NEW.requestid FROM dual;
END;


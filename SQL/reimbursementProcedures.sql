---USEID IS JUST THE PARAMETER VERSION OF USERID 
/*CREATE OR REPLACE PROCEDURE showMyRequests(useid IN NUMBER)
AS BEGIN
select * from request where request.userid=useid order by eventdate;
COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE showSupRequests(useid IN NUMBER)
AS BEGIN
select * from request join employee on request.userid=employee.userid where employee.reportsto=useid order by eventdate;
COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE showDeptRequests(deptid IN NUMBER)
AS BEGIN
select * from request join employee on request.userid=employee.userid where employee.department=deptid order by eventdate;
COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE showRequests
AS BEGIN
select * from request order by eventdate;
COMMIT;
END;
/
*/
select docs from attachment where requestid = 23 and userid = 1;
insert into attachment values(1,44,'some.other.links');
/*authenticates password*/
--select pass from employee where username=uname;

/*gets UserID*/
--Select userid from employee where username=uname;

/*gets title*/
--Select titlename from title inner join employee on title.titleid=employee.authority where employee.userid=useid;

/*gets department name*/
--Select deptname from title inner join employee on department.deptid=employee.department where employee.userid=useid;

/*get application status*/
--select status from request inner join employee on request.userid=employee.userid where ?=?;


CREATE OR REPLACE PROCEDURE insertRec(useId number, ename varchar2, eloc varchar2,
    edate date, edesc varchar2, etype number, gscale number, pgrade varchar2, justifi varchar2,
    ecost number, reimburs number, links varchar2)
AS BEGIN
insert into request(userID, eventname, eventlocation, eventdate, eventdescription, eventtype, gradingScale,
            passingGrade, justification, eventcost, reimbursment,docs ) values (useId, ename, eloc, edate, edesc, etype, gscale, pgrade, 
            justifi, ecost, reimburs,links);
END;
/

insert into request(userID, eventname, eventlocation, eventdate, eventdescription, eventtype, gradingScale,
            passingGrade, justification, eventcost, reimbursment,docs ) values (1, 'test', 'test', '20-JAN-20', 'test', 1, 1, 'test', 
            'test', 100, 50,'test.link');


CREATE OR REPLACE PROCEDURE updateRemains(useid in number, bal in number)
AS BEGIN
update employee set remainingBal=bal where userid=useid;
commit;
END;
/
CREATE OR REPLACE PROCEDURE updateReimburse(recid in number, bal in number)
AS BEGIN
update request set reimbursment=bal where requestid=recid;
commit;
END;
/
CREATE OR REPLACE PROCEDURE updateStatus(authority in number, recid in number)
AS BEGIN
update request set status=authority where requestid=recid;
commit;
END;
/

CREATE OR REPLACE PROCEDURE updateFlagged(recid in number, authority in number)
AS BEGIN
update request set flagged=authority where requestid=recid;
commit;
END;
/

exec updateFlagged(42,0);

select recseq.currval from dual;

select * from request join employee on request.userid=employee.userid where status = 0 and employee.reportsto=3 and flagged = -1 order by eventdate
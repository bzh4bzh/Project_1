--select sum(reimbursment) from request where status!=-1 and status!=3 and userid=?;
--select remainingBal from employee where userid=?;

--show a user all their requests approved or denied
--select * from request where request.userid=? order by eventdate;

--show requests to direct sup
--select * from request join employee on request.userid=employee.userid where status=0 and employee.reportsto=? order by eventdate;

--show requests to dept head
--select * from request join employee on request.userid=employee.userid where status=1 and employee.department=? order by eventdate; 

--show requests to benco
--select * from request where status=2 order by eventdate;

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
    ecost number, reimburs number)
AS BEGIN
insert into request(userID, eventname, eventlocation, eventdate, eventdescription, eventtype, gradingScale,
            passingGrade, justification, eventcost, reimbursment) values (useId, ename, eloc, edate, edesc, etype, gscale, pgrade, 
            justifi, ecost, reimburs);
END;
/

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

create or replace procedure updateInfo(newInfo in varchar2, recid in number)
as begin
update request set moreinfo=newInfo where requestid=recid;
commit;
end;
/

create or replace procedure updateFinalGrade(grade in varchar2, recid in number)
as begin
update request set finalgrade=grade where requestid=recid;
commit;
end;
/
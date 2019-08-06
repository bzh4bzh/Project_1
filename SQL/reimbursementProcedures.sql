CREATE OR REPLACE PROCEDURE showMyRequests(useid IN NUMBER)
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







CREATE OR REPLACE PROCEDURE authenticate(uname IN varchar2)
AS BEGIN
select pass from employee where username=uname;
COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE getUID(uname IN varchar2)
AS BEGIN
Select userid from employee where username=uname;
COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE getAuthority(useid IN number)
AS BEGIN
Select titlename from title inner join employee on title.titleid=employee.authority where employee.userid=useid;
--COMMIT;
END;
/






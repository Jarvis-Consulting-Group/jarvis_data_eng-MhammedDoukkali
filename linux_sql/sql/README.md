# Inroduction
This is a learning project which allow to learn SQL and RDBMS by solving SQL queries.

###### INSERT DATA 

Solution : `insert into cd.facilities
	#the name's value are optional
    (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
    values (9, 'Spa', 20, 30, 100000, 800); `

###### INSERT automatically generate the value for the next row using select 

inset and select :` insert into cd.facilities
    (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
    select (select max(facid) from cd.facilities)+1, 'Spa', 20, 30, 100000, 800;`

###### UPDATE VALUE 

`UPDATE cd.facilities 
SET initialoutlay = 10000
WHERE facid = 1;`

###### UPDATE WITH CALCULATION 

`update cd.facilities facs
    set
        membercost = facs2.membercost * 1.1,
        guestcost = facs2.guestcost * 1.1
    from (select * from cd.facilities where facid = 0) facs2
    where facs.facid = 1;`

###### DELETE ALL 
`DELETE from cd.bookings;`

###### DELETE WITH CONDITION

`DELETE FROM cd.members 
WHERE memid = 37;`

###### Control which rows are retrieved

`select facid, name, membercost, monthlymaintenance 
	from cd.facilities 
	where 
		membercost > 0 and 
		(membercost < monthlymaintenance/50.0); `

###### Basic string searches

`SELECT * FROM cd.facilities
WHERE name LIKE '%Tennis%';`

###### Matching against multiple possible values
`select * 
	from cd.facilities
	where
		facid in (
			select facid from cd.facilities
			);`

###### Working with dates

`SELECT memid, surname, firstname,joindate 
     FROM cd.members
	     WHERE joindate >= '2012-09-01';`

###### Combining results from multiple queries

`select surname 
	from cd.members
union
select name
	from cd.facilities;        `  

###### Retrieve the start times of members' bookings Using inner join 

`select bks.starttime 
	from 
		cd.bookings bks
		inner join cd.members mems
			on mems.memid = bks.memid
	where 
		mems.firstname='David' 
		and mems.surname='Farrell';  `

###### - Work out the start times of bookings for tennis courts
###### Inner joins take a left and a right table, and look for matching rows based on a join condition (ON). When the condition is satisfied, a joined row is produced.

`select bks.starttime as start, facs.name as name
	from 
		cd.facilities facs
		inner join cd.bookings bks
			on facs.facid = bks.facid
	where 
		facs.name in ('Tennis Court 2','Tennis Court 1') and
		bks.starttime >= '2012-09-21' and
		bks.starttime < '2012-09-22'
order by bks.starttime;`          

###### Produce a list of all members, along with their recommender
###### A LEFT OUTER JOIN operates similarly, except that if a given row on the left hand table doesn't match anything, it still produces an output row. That output row consists of the left hand table row, and a bunch of NULLS in place of the right hand table row.

`select mems.firstname as memfname, mems.surname as memsname, recs.firstname as recfname, recs.surname as recsname
	from 
		cd.members mems
		left outer join cd.members recs
			on recs.memid = mems.recommendedby
order by memsname, memfname; `  

###### Produce a list of all members who have recommended another member

`select distinct recs.firstname as firstname, recs.surname as surname
	from 
		cd.members mems
		inner join cd.members recs
			on recs.memid = mems.recommendedby
order by surname, firstname;`

###### Produce a list of all members, along with their recommender, using no joins.

`select distinct mems.firstname || ' ' ||  mems.surname as member,
	(select recs.firstname || ' ' || recs.surname as recommender 
		from cd.members recs 
		where recs.memid = mems.recommendedby
	)
	from 
		cd.members mems
order by member;      `

###### Count the number of recommendations each member makes 

`select recommendedby, count(*) 
	from cd.members
	where recommendedby is not null
	group by recommendedby
order by recommendedby;`

###### List the total slots booked per facility

`select facid, sum(slots) as "Total Slots"
	from cd.bookings
	group by facid
order by facid;   `

###### List the total slots booked per facility in a given month

`select facid, sum(slots) as "Total Slots"
	from cd.bookings
	where
		starttime >= '2012-09-01'
		and starttime < '2012-10-01'
	group by facid
order by sum(slots);    `

###### List the total slots booked per facility per month
###### The main piece of new functionality in this question is the EXTRACT function. EXTRACT allows you to get individual components of a timestamp, like day, month, year, etc. We group by the output of this function to provide per-month values. An alternative, if we needed to distinguish between the same month in different years, is to make use of the DATE_TRUNC function, which truncates a date to a given granularity. It's also worth noting that this is the first time we've truly made use of the ability to group by more than one column

`select facid, extract(month from starttime) as month, sum(slots) as "Total Slots"
	from cd.bookings
	where extract(year from starttime) = 2012
	group by facid, month
order by facid, month;`  

###### List each member's first booking after September 1st 2012
###### This answer demonstrates the use of aggregate functions on dates. MIN works exactly as you'd expect, pulling out the lowest possible date in the result set. To make this work, we need to ensure that the result set only contains dates from September onwards. We do this using the WHERE clause.

`select mems.surname, mems.firstname, mems.memid, min(bks.starttime) as starttime
	from cd.bookings bks
	inner join cd.members mems on
		mems.memid = bks.memid
	where starttime >= '2012-09-01'
	group by mems.surname, mems.firstname, mems.memid
order by mems.memid;  `

###### Produce a list of member names, with each row containing the total member count

`select count(*) over(), firstname, surname
	from cd.members
order by joindate       ` 

###### Produce a numbered list of members 
###### This exercise is a simple bit of window function practise! You could just as easily use count(*) over(order by joindate) here, so don't worry if you used that instead.

`select row_number() over(order by joindate), firstname, surname
	from cd.members
order by joindate`     `

###### Output the facility id that has the highest number of slots booked, again

`select facid, total from (
	select facid, sum(slots) total, rank() over (order by sum(slots) desc) rank
        	from cd.bookings
		group by facid
	) as ranked
	where rank = 1      `

`select facid, sum(slots) as totalslots
	from cd.bookings
	group by facid
	having sum(slots) = (select max(sum2.totalslots) from
		(select sum(slots) as totalslots
		from cd.bookings
		group by facid
		) as sum2);`



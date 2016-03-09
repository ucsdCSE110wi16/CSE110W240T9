# myCalendar
An android calendar app designed for students!

Library:
* Android Week View library by alamkanak (Under Apache License)



Summary
--------------
mycalendar is an android calendar app used for students to keep track of classes and events.

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/ic_calender_finished.png" align="center" height="256" width="256" >

Features w/User Stories
--------------
* __Creating new Event: Static Event__ (can be periodic) - As a student, I want the app to statically schedule my events/activites so that I know when the events will be occur each week.

* __Creating new Event: Dynamic Event__ (Non periodic, can locate a time for itself) - As a student, I want the app to dynamically schedule my events/activities so that the events can change to fit my needs

* __Add finished event to finished List__ - As a student, I want the app to be keep a list of finished events so that I can look back on my finished list events to see what I have finished or so that I can reference what I have doe before.

* __Show month view of calendar (Main View)__ - As a student, I want the app to show a month view so that I can see upcoming events in the month.

* __Allow scroll to future and past month view of calendar (Main View)__ - As a student, I want the app to be able to swipe left and right to future and past months so that I am able to plan for upcoming events in future montsh or look back and referenece past events in past months.

* __Opening existing event__ - As a student, I want the app to be able to open an existing event so that I can edit the details of the event.

* __Updating existing event__ - As a student, I want the app to be able to update an existing event so that I can change the details of the event should they chagne.

* __Deleting Event__ - As a student, I want the app to be able to delete an event so that I can edit my upcoming schedules in case I can no longer do/attend that event or no longer need/want to do/attend that event.

Test Scenarios
--------------
* All fields on page (e.g. text box, radio options, dropdown lists) should be aligned properly
* Numeric values should be right justified unless specified otherwise
* Enough space should be provided between field labels, columns, rows, error messages etc.
* All buttons on page should be able to perform correctly
* Each calendar view should be Scrollable
* Both date and time should be Scrollable
* Main calendar should have all events listed
* Event detail should be accessible when clicking on it  
* __Adding static event and having it allocated on the main calendar correctly__
* __Add in dynamic event, having it fill in the free time zone only before deadline__
* __Dynamic events should not overlapped with the static events__
* Events should turn grey when user archived them
* Existing events are available for editing.
* Existing events are available for deleting
* we have the user login system existing calendar can be upload to cloud.

GUI Testing
--------------

__For our app, the functionality needs continuous testing and improvement. That's why we feel human testing is appropriate. There are many cases that we need to consider, and many different times we need to test for our algorithm.__

__Test Machine Name: Siyu Yang__

__Test Report, GUI Test:__

Main view:
*The app launches without crashing.....PASS
*The menu button is clickable.....PASS
*The top right button shows 3 different views.....PASS
*Clicking on the day view generates the day view.....PASS
*Clicking on the 3 day view generates the 3 day view.....PASS
*CLicking on the week view generates the week view.....PASS
*Scrolling left makes the calendar show previous days.....PASS
*Scrolling right makes the calendar show upcoming days.....PASS
*Scrolling up makes the calendar show previous times of the day.....PASS
*Scrolling down makes the calendar show upcoming times of the day.....PASS
*Clicking on today makes the calendar jump to the current day.....PASS
*Clicking on the + button on the bottom right corner makes the calendar open the add event page.....PASS
*If events are conflicted, the events will show up side by side.....PASS
*Black lines shows the current time.....PASS

Event summary page:
*Clicking on a dynamic event will take you to another page showing the estimated length, and deadline.....PASS
*Clicking on a static event will take you to another page showing the static event's start time and end time.....PASS
*Clicking on the edit button in this view will open the event view.....PASS

Add event view:
*Clicking on the X button on the top left corner in the add event page returns the calendar to the main view.....PASS
*Clicking on enter event name will open keyboard.....PASS
*Clicking on location will open keyboard.....PASS
*Clicking on Static will let you choose between static and dynamic.....PASS
*Clicking on TEAL will let you select the color, the color will change instantly.....PASS
*Clicking on Notes will open keyboard.....PASS
*Clicking on set Start Date/End Date will open Date picker.....PASS
*Clicking on set Start Time/End Time will open Time picker.....PASS
*The date picker can pick dates and display correctly.....PASS
*The time picker can pick times and display correctly.....PASS
*If any fields are not edited, notification that not all fields are inputted.....PASS
*Selecting dynamic will change the view to have deadline dates, and estimated hours instead of the static event input fields.....PASS
*If dynamic deadline is before current time,notify user that field is invalid.....PASS
*Event duration can only be a number.....PASS
*The calendar can correctly add a dynamic event.....PASS
*The calendar can correctly add a static event.....PASS

Weekly Project Schedule
--------------
__Week 1 & Week 2__
* ~~Learning and writing user stories (everyone)~~

__Week 3__
* ~~Learning and Writing user stories (everyone)~~
* ~~Prototyping UI (everyone)~~
* ~~Look for libraries that can help us out with calendar view~~

__Week 4- 5__
* ~~Integrated with Material View Calendar View Library~~
* ~~Finished prototyping UI (everyone)~~
* ~~Iterate over user stories to eliminate assumption (everyone)~~
* ~~Working the ability to show month view of calendar, which is the main view (Kristine, Fang, Alex, William)~~
* ~~Prototyping event self allocation algorithm (Scarlet, Steve)~~
* ~~Prototyping Database (Zhiwei)~~
* ~~Testing the main view.~~

__Week 6__
* ~~Iterate over user stories to eliminate assumption (everyone)~~
* ~~Finished working the ability to show month view of calendar and testing the main view (Kristine, Fang, Alex, William)~~
* ~~Keep working on Database (Zhiwei)~~
*~~Creating new Event: Static Event (can be periodic)~~
* ~~Creating new Event: Dynamic Event (Nonperiodic, can locate a time for itself)~~
* ~~Updating in database. (Kristine, Fang, Alex, William, Zhiwei)~~
* ~~Prototyping and testing event self-allocation algorithm. (Scarlet, Steve)~~


__Week 7__
* ~~Keep working on as well as testing Creating new event mechanism. (Kristine, Fang, Alex, William, Zhiwei)~~
* ~~Integrate show month view with create new event view and create smooth transitions in between. (Kristine, Fang, Alex, William)~~
* ~~add datepicker function~~
* ~~Finishing event self-allocation algorithm. (Scarlet, Steve)~~
* ~~Deleting event and updating deleted events in database. (Zhiwei)~~
* ~~Updating UI using Material Design. (Kristine, Fang, Alex, William)~~

__Week 8__
* ~~add finished event to finished List (Kristine, Fang, Alex, William, Zhiwei)~~
* ~~renamed folder and added finished for events list fragment~~
* ~~changed calendar class and improved naming and functionality~~
* ~~Testing on basic features such as adding and deleting events. (everyone)~~
* ~~added color field~~
*  ~~merge conflict~~

__Week 9__ 
* Add notification
* ~~merge the Dynamic event algorithm with the front end~~
* ~~merge the self allocation algorithm with the front end~~
* ~~merge the event delete algorithm with the front end~~
* ~~added freetimesort~~
* ~~Testing on basic features~~
* ~~back end algorithm update~~
* ~~add Priorityqueue~~
* ~~edit event handler~~
* ~~fixed date picker and time picker in add event activity~~
* ~~fix date formatting error~~
* ~~merge conflict~~
* ~~set event active after archiving it~~
* Finish product

__Week 10__ 
* Final testing and maintenance.
* User testing

__Week 11__
* Presentation


Iteration 1
--------------
<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/screenShot1.png" align="center" height="256" width="256" >

Iteration 2
--------------
<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/material_1.png" align="center" height="auto" width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/material_2.png" align="center" height= auto width="256" >

Iteration 3
--------------
<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/official_1.png" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/official_2.png" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/official_3.png" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/official_4.png" align="center" height=auto width="256" >
 

Iteration 4
--------------
<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration4_addEvent.png" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration4_addEventPage.png" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration4_eventDescription.png" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration4_eventDetailMenu.png" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration4_setEventFinishedAsGray.png" align="center" height=auto width="256" >


Iteration 5
--------------
<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration5_1.jpg" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration5_2.jpg" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration5_3.jpg" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration5_4.jpg" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration5_5.jpg" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration5_6.jpg" align="center" height=auto width="256" >

<img src="https://raw.githubusercontent.com/ucsdCSE110wi16/CSE110W240T9/master/images/iteration5_7.jpg" align="center" height=auto width="256" >

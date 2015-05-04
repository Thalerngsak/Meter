/*
    Author        :   Tan Ling	Wee
    Copyright     :   (C) 2002 Hutchison 3G
    Description   :   
    email         :   fuushikaden@yahoo.com
    written on    :   2 Dec 2001
    lastupdated   :   23 June 2002
*/

	var fixedX = -1     // x position (-1 if to appear below control)
	var fixedY = -1			// y position (-1 if to appear below control)
	var startAt = 1			// 0 - sunday ; 1 - monday
	var showWeekNumber = 0	    // 0 - don't show; 1 - show
	var showToday = 1		// 0 - don't show; 1 - show
	var imgDir = "calendar/"	// directory for images ... e.g. var imgDir="/img/"

	var gotoString = "Go To Current Month"
	var todayString = "Today is "
	//var todayString = "?????? "
	var weekString = "Wk"
	var scrollLeftMessage = "Click to scroll to previous month. Hold mouse button to scroll automatically."
	var scrollRightMessage = "Click to scroll to next month. Hold mouse button to scroll automatically."
	var selectMonthMessage = "Click to select a month."
	var selectYearMessage = "Click to select a year."
	var selectDateMessage = "Select [date] as date." // do not replace [date], it will be replaced by date.

	var	crossobj, crossMonthObj, crossYearObj, monthSelected, yearSelected, dateSelected, omonthSelected, oyearSelected, odateSelected, monthConstructed, yearConstructed, intervalID1, intervalID2, timeoutID1, timeoutID2, ctlToPlaceValue, ctlNow, dateFormat, nStartingYear, ctlDay, ctlMonth, ctlYear, yearLen, monthLen

	var	bPageLoaded=false
	var	ie=document.all
	var	dom=document.getElementById

	var	ns4=document.layers
	var	today =	new	Date()
	var	dateNow	 = today.getDate()
	var	monthNow = today.getMonth()
	var	yearNow	 = today.getYear() 
	var	imgsrc = new Array("drop1.gif","drop2.gif","left1.gif","left2.gif","right1.gif","right2.gif")
	var	img	= new Array()
	var bShow = false;
	
	var paramDateField = -1;

    /* hides <select> and <applet> objects (for IE only) */
    function hideElement( elmID, overDiv )
    {
      if (true)//if( ie ) // TODO: firefox
      {
        for( i = 0; i < document.all.tags( elmID ).length; i++ )
        {
          obj = document.all.tags( elmID )[i];
          if( !obj || !obj.offsetParent )
          {
            continue;
          }
      
          // Find the element's offsetTop and offsetLeft relative to the BODY tag.
          objLeft   = obj.offsetLeft;
          objTop    = obj.offsetTop;
          objParent = obj.offsetParent;
          
          while( objParent.tagName.toUpperCase() != "BODY" )
          {
            objLeft  += objParent.offsetLeft;
            objTop   += objParent.offsetTop;
            objParent = objParent.offsetParent;
          }
      
          objHeight = obj.offsetHeight;
          objWidth = obj.offsetWidth;
      
          if(( overDiv.offsetLeft + overDiv.offsetWidth ) <= objLeft );
          else if(( overDiv.offsetTop + overDiv.offsetHeight ) <= objTop );
          else if( overDiv.offsetTop >= ( objTop + objHeight ));
          else if( overDiv.offsetLeft >= ( objLeft + objWidth ));
          else
          {
            obj.style.visibility = "hidden";
          }
        }
      }
    }
     
    /*
    * unhides <select> and <applet> objects (for IE only)
    */
    function showElement( elmID )
    {
     if (true)//if( ie ) // TODO: firefox
      {
        for( i = 0; i < document.all.tags( elmID ).length; i++ )
        {
          obj = document.all.tags( elmID )[i];
          
          if( !obj || !obj.offsetParent )
          {
            continue;
          }
        
          obj.style.visibility = "";
        }
      }
    }

	function HolidayRec (d, m, y, desc)
	{
		this.d = d
		this.m = m
		this.y = y
		this.desc = desc
	}

	var HolidaysCounter = 0
	var Holidays = new Array()

	function addHoliday (d, m, y, desc)
	{
		Holidays[HolidaysCounter++] = new HolidayRec ( d, m, y, desc )
	}

	if (dom)
	{
		for	(i=0;i<imgsrc.length;i++)
		{
			img[i] = new Image
			img[i].src = imgDir + imgsrc[i]
		}
		document.write ("<div onclick='bShow=true' id='calendar'	style='z-index:+999;position:absolute;visibility:hidden;'><iframe frameborder='0' src='javascript:;' style=' width : 100%; height:100%;  position: absolute; z-index:-1; visibility:visible ;  filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)'></iframe><table	width="+((showWeekNumber==1)?250:220)+" style='font-family:arial;font-size:11px;border-width:1;border-style:solid;border-color:#a0a0a0;font-family:arial; font-size:11px}' bgcolor='#ffffff'><tr bgcolor='#F1DAAF'><td><table width='"+((showWeekNumber==1)?248:218)+"'><tr><td style='padding:2px;font-family:arial; font-size:11px;'><font color='#754C24'><B><span id='caption'></span></B></font></td><td align=right><a href='javascript:hideCalendar()'><IMG SRC='"+imgDir+"close.gif' WIDTH='15' HEIGHT='13' BORDER='0' ALT='Close the Calendar'></a></td></tr></table></td></tr><tr><td style='padding:2px' bgcolor=#ffffff><span id='content'></span></td></tr>")
			
		if (showToday==1)
		{
			document.write ("<tr bgcolor=#f0f0f0><td style='padding:0px' align=center><span id='lblToday' style='font-family:arial;font-size:11px;color:#9E0B0E;'></span></td></tr>")
		}
			
		document.write ("</table></div><div id='selectMonth' style='z-index:+999;position:absolute;visibility:hidden;'></div><div id='selectYear' style='z-index:+999;position:absolute;visibility:hidden;'></div>");
	}

	var	monthName =	new	Array("มกราคม","กุมภาพันธ์","มีนาคม","เมษายน","พฤษภาคม","มิถุนายน","กรกฎาคม","สิงหาคม","กันยายน","ตุลาคม","พฤศจิกายน","ธันวาคม")
	//var	monthName =	new	Array("??????","??????????","??????","??????","???????","????????","???????","???????","???????","??????","?????????","???????")
	if (startAt==0)
	{
		dayName = new Array	("Sun","Mon","Tue","Wed","Thu","Fri","Sat")
		dayNameLong = new Array	("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
		//dayName = new Array	("??","?","?","?","??","?","?")
		//dayNameLong = new Array	("???????","??????","??????","???","?????","?????","?????")
	}
	else
	{
		dayName = new Array	("Mon","Tue","Wed","Thu","Fri","Sat","Sun")
		dayNameLong = new Array	("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")		
		//dayName = new Array	("?","?","?","??","?","?","??")
		//dayNameLong = new Array	("??????","??????","???","?????","?????","?????","???????")
	}
	var	styleAnchor="text-decoration:none;color:#303030;font-family:arial;font-size:11px;"
	var	styleLightBorder="border-style:solid;border-width:1px;border-color:#a0a0a0;"

	function swapImage(srcImg, destImg){
		/*if (ie)	{ document.getElementById(srcImg).setAttribute("src",imgDir + destImg) }*/
	}

	function init()	{		
		if (!ns4)
		{
			//if (!ie) { yearNow += 1900	}

			crossobj=(dom)?document.getElementById("calendar").style : document.all.calendar 
			hideCalendar()

			crossMonthObj=(dom)?document.getElementById("selectMonth").style : document.all.selectMonth

			crossYearObj=(dom)?document.getElementById("selectYear").style :document.all.selectYear 

			monthConstructed=false;
			yearConstructed=false;
			
					
			yearNow += 543; //TODO:init() year display 2551	
			
			if (showToday==1)
			{
				//change display at lower of popup
				//document.getElementById("lblToday").innerHTML =	todayString + " <a onmousemove='window.status=\""+gotoString+"\"' onmouseout='window.status=\"\"' title='"+gotoString+"' style='"+styleAnchor+"' href='javascript:monthSelected=monthNow;yearSelected=yearNow;constructCalendar();'>"+dayName[(today.getDay()-startAt==-1)?6:(today.getDay()-startAt)]+", " + dateNow + " " + monthName[monthNow].substring(0,3)	+ "	" +	(yearNow+543) + "</a>"
				document.getElementById("lblToday").innerHTML =	todayString + " <a onmousemove='window.status=\""+gotoString+"\"' onmouseout='window.status=\"\"' title='"+gotoString+"' style='"+styleAnchor+"' href='javascript:monthSelected=monthNow;yearSelected=yearNow;constructCalendar();'>"+dayNameLong[(today.getDay()-startAt==-1)?6:(today.getDay())]+" " + dateNow + " " + monthName[monthNow]	+ "	" +	(yearNow) + "</a>"
			}

			sHTML1="<span id='spanLeft'	style='border-style:solid;border-width:1;border-color:#A0A0A0;cursor:pointer' onmouseover='swapImage(\"changeLeft\",\"left2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+scrollLeftMessage+"\"' onclick='javascript:decMonth()' onmouseout='clearInterval(intervalID1);swapImage(\"changeLeft\",\"left1.gif\");this.style.borderColor=\"#A0A0A0\";window.status=\"\"' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartDecMonth()\",500)'	onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)'>&nbsp<IMG id='changeLeft' SRC='"+imgDir+"left1.gif' BORDER=0>&nbsp</span>&nbsp;"
			sHTML1+="<span id='spanRight' style='border-style:solid;border-width:1;border-color:#A0A0A0;cursor:pointer'	onmouseover='swapImage(\"changeRight\",\"right2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+scrollRightMessage+"\"' onmouseout='clearInterval(intervalID1);swapImage(\"changeRight\",\"right1.gif\");this.style.borderColor=\"#A0A0A0\";window.status=\"\"' onclick='incMonth()' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartIncMonth()\",500)'	onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)'>&nbsp<IMG id='changeRight' SRC='"+imgDir+"right1.gif'	BORDER=0>&nbsp</span>&nbsp"
			sHTML1+="<span id='spanMonth' style='border-style:solid;border-width:1;border-color:#A0A0A0;cursor:pointer'	onmouseover='swapImage(\"changeMonth\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+selectMonthMessage+"\"' onmouseout='swapImage(\"changeMonth\",\"drop1.gif\");this.style.borderColor=\"#A0A0A0\";window.status=\"\"' onclick='popUpMonth()'></span>&nbsp;"
			sHTML1+="<span id='spanYear' style='border-style:solid;border-width:1;border-color:#A0A0A0;cursor:pointer' onmouseover='swapImage(\"changeYear\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+selectYearMessage+"\"'	onmouseout='swapImage(\"changeYear\",\"drop1.gif\");this.style.borderColor=\"#A0A0A0\";window.status=\"\"'	onclick='popUpYear()'></span>&nbsp;"

			
			document.getElementById("caption").innerHTML  =	sHTML1
			yearNow -= 543; //TODO:init() set back for calulate 
			
			bPageLoaded=true
		}
	}

	function hideCalendar()	{
		
		crossobj.visibility="hidden"
		if (crossMonthObj != null){crossMonthObj.visibility="hidden"}
		if (crossYearObj !=	null){crossYearObj.visibility="hidden"}
		document.getElementById("calendar").children[0].style.display = 'none';

	    //showElement( 'SELECT' );
		//showElement( 'APPLET' );
	}

	function padZero(num) {
		return (num	< 10)? '0' + num : num ;
	}

	function constructDate(d,m,y)
	{
		sTmp = dateFormat
		sTmp = sTmp.replace	("dd","<e>")
		sTmp = sTmp.replace	("d","<d>")
		sTmp = sTmp.replace	("<e>",padZero(d))
		sTmp = sTmp.replace	("<d>",d)
		sTmp = sTmp.replace	("mmm","<o>")
		sTmp = sTmp.replace	("mm","<n>")
		sTmp = sTmp.replace	("m","<m>")
		sTmp = sTmp.replace	("<m>",m+1)
		sTmp = sTmp.replace	("<n>",padZero(m+1))
		sTmp = sTmp.replace	("<o>",monthName[m])
		return sTmp.replace ("yyyy",y)
	}

	function closeCalendar() {
            var sTmp
            hideCalendar();
		// change out put for D.C. and A.C. at line below
	    //ctlToPlaceValue.value =	constructDate(dateSelected,monthSelected,yearSelected+0)
            ctlToPlaceValue.value =	constructDate(dateSelected,monthSelected,yearSelected+543)
            dateVal = ctlToPlaceValue.value
            if(ctlDay!=null) {
               dzTmp = parseInt(dateSelected);
               if (dzTmp && dzTmp<10 && dateFormat.indexOf('mm')!=-1) {
                  dzTmp = "0" + dzTmp;
               }
               ctlDay.value = dzTmp;
			   //alert(dzTmp);
           }
           if(ctlMonth!=null) {
               if (ctlMonth.value==11) {
      	           ctlMonth.value =  monthSelected;
               } else {
                   mozTmp = parseInt(monthSelected + 1);
                   if (mozTmp>0 && mozTmp<10 && dateFormat.indexOf('mm')!=-1) {
                       mozTmp = "0" + mozTmp
                   }
                   ctlMonth.value = mozTmp;
				   //alert(mozTmp);
               }
           }
           if(ctlYear!=null) { 
              ctlYear.value  =  yearSelected;
			  //alert(yearSelected);
           }


			//calculate for age/period
		   var nowDate = new Date();
		   nowY = nowDate.getYear();
		   if( nowY < 1900 )
			{
			   //getYear return 00-99, mean in range 1900-1999
			   nowY += 1900;
			}
		   nowM = nowDate.getMonth()+1;
		   nowD = nowDate.getDate();

			selY = parseInt(yearSelected);
			selM = parseInt(monthSelected+1);
			selD = dzTmp;

			nowY = nowY-selY;
			nowM = nowM-selM;
			nowD = nowD-selD;
			if( nowD > 25 )
			{
				//ceil for a month
				nowM = nowM+1;
			}

			if( nowY < 0 )
			{
				//wrong input year
				return;
			}else
			{
				if( nowM < 0 )
				{
					if( nowY == 0 )
					{
						//wrong input month at year = now year
						return;
					}

					nowY = nowY-1;
					nowM = 12+nowM;
				}

				if( yearLen != null )
			   {
					yearLen.value = nowY;
			   }

			   if( monthLen != null )
			   {
				   monthLen.value = nowM;
			   }
			}
	}


	/*** Month Pulldown	***/

	function StartDecMonth()
	{
		intervalID1=setInterval("decMonth()",80)
	}

	function StartIncMonth()
	{
		intervalID1=setInterval("incMonth()",80)
	}

	function incMonth () {
		monthSelected++
		if (monthSelected>11) {
			monthSelected=0
			yearSelected++
		}
		constructCalendar()
	}

	function decMonth () {
		monthSelected--
		if (monthSelected<0) {
			monthSelected=11
			yearSelected--
		}
		constructCalendar()
	}

	function constructMonth() {
		popDownYear()
		if (!monthConstructed) {
			sHTML =	""
			for	(i=0; i<12;	i++) {
				sName =	monthName[i];
				if (i==monthSelected){
					sName =	"<B>" +	sName +	"</B>"
				}
				sHTML += "<tr><td id='m" + i + "' style='font-family:arial; font-size:11px;' onmouseover='this.style.backgroundColor=\"#F1DAAF\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' onclick='monthConstructed=false;monthSelected=" + i + ";constructCalendar();popDownMonth();event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>"
			}

			document.getElementById("selectMonth").innerHTML = "<table width=70	style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;' bgcolor='#FFFFDD' cellspacing=0 onmouseover='clearTimeout(timeoutID1)'	onmouseout='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"popDownMonth()\",100);event.cancelBubble=true'>" +	sHTML +	"</table>"

			monthConstructed=true
		}
	}

	function popUpMonth() {
		constructMonth()
		crossMonthObj.visibility = "visible"
		crossMonthObj.left = parseInt(crossobj.left) + 50
		crossMonthObj.top =	parseInt(crossobj.top) + 26

		hideElement( 'SELECT', document.getElementById("selectMonth") );
		hideElement( 'APPLET', document.getElementById("selectMonth") );			
	}

	function popDownMonth()	{
		crossMonthObj.visibility= "hidden"
	}

	/*** Year Pulldown ***/

	function incYear() {
		nStartingYear+= 543; //TODO:incYear() year display
		for	(i=0; i<7; i++){
			
			newYear	= (i+nStartingYear)+1
			if (newYear==yearSelected)
			{ txtYear =	"&nbsp;<B>"	+ (newYear) +"</B>&nbsp;" }
			else
			{ txtYear =	"&nbsp;" + (newYear) +"&nbsp;" }
			document.getElementById("y"+i).innerHTML = txtYear
		}
		nStartingYear -= 543; //TODO:incYear() set back for calulate 
		nStartingYear ++;
		bShow=true
	}

	function decYear() {
		nStartingYear+= 543; //TODO:decYear() year display
		for	(i=0; i<7; i++){
			newYear	= (i+nStartingYear)-1
			if (newYear==yearSelected)
			{ txtYear =	"&nbsp;<B>"	+(newYear)+"</B>&nbsp;" }
			else
			{ txtYear =	"&nbsp;" +(newYear)+"&nbsp;" }
			document.getElementById("y"+i).innerHTML = txtYear
		}
		nStartingYear -= 543; //TODO:decYear() set back for calulate 
		nStartingYear --;
		bShow=true
	}

	function selectYear(nYear) {
		yearSelected=parseInt(nYear+nStartingYear);
		yearConstructed=false;
		constructCalendar();
		popDownYear();
	}

	function constructYear() {
		popDownMonth()
		sHTML =	""
		if (!yearConstructed) {
			
			sHTML =	"<tr><td align='center'	style='font-family:arial; font-size:11px;' onmouseover='this.style.backgroundColor=\"#F1DAAF\"' onmouseout='clearInterval(intervalID1);this.style.backgroundColor=\"\"' style='cursor:pointer'	onmousedown='clearInterval(intervalID1);intervalID1=setInterval(\"decYear()\",30)' onmouseup='clearInterval(intervalID1)'>-</td></tr>"

			j =	0
			nStartingYear =	yearSelected-3	
			yearSelected += 543; //TODO:construtYear(): display 2551	
			for	(i=(yearSelected-3); i<=(yearSelected+3); i++) {
				sName =	i;
				if (i==yearSelected){
					sName =	"<B>" +(sName)+"</B>"
				}else
				{
					sName =	(sName)
				}
				
				sHTML += "<tr><td id='y" + j + "' style='font-family:arial; font-size:11px;' onmouseover='this.style.backgroundColor=\"#F1DAAF\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' onclick='selectYear("+j+");event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>"
				
				j ++;
				
				
			}
			yearSelected -= 543; //TODO:construtYear(): set back
			
			sHTML += "<tr><td align='center' style='font-family:arial; font-size:11px;' onmouseover='this.style.backgroundColor=\"#F1DAAF\"' onmouseout='clearInterval(intervalID2);this.style.backgroundColor=\"\"' style='cursor:pointer' onmousedown='clearInterval(intervalID2);intervalID2=setInterval(\"incYear()\",30)'	onmouseup='clearInterval(intervalID2)'>+</td></tr>"
			document.getElementById("selectYear").innerHTML	= "<table width=44 style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;'	bgcolor='#FFFFDD' onmouseover='clearTimeout(timeoutID2)' onmouseout='clearTimeout(timeoutID2);timeoutID2=setTimeout(\"popDownYear()\",100)' cellspacing=0>"	+ sHTML	+ "</table>"
			
			yearConstructed	= true
		}
	}

	function popDownYear() {
		clearInterval(intervalID1)
		clearTimeout(timeoutID1)
		clearInterval(intervalID2)
		clearTimeout(timeoutID2)
		crossYearObj.visibility= "hidden"
	}

	function popUpYear() {
		var	leftOffset
		constructYear()
		crossYearObj.visibility	= "visible" 
		leftOffset = parseInt(crossobj.left) + document.getElementById("spanYear").offsetLeft
	
			leftOffset += 6
		
		crossYearObj.left =	leftOffset
		crossYearObj.top = parseInt(crossobj.top) +	26
	}

	/*** calendar ***/
   function WeekNbr(n) {
      // Algorithm used:
      // From Klaus Tondering's Calendar document (The Authority/Guru)
      // hhtp://www.tondering.dk/claus/calendar.html
      // a = (14-month) / 12
      // y = year + 4800 - a
      // m = month + 12a - 3
      // J = day + (153m + 2) / 5 + 365y + y / 4 - y / 100 + y / 400 - 32045
      // d4 = (J + 31741 - (J mod 7)) mod 146097 mod 36524 mod 1461
      // L = d4 / 1460
      // d1 = ((d4 - L) mod 365) + L
      // WeekNumber = d1 / 7 + 1
 
      year = n.getFullYear();
      month = n.getMonth() + 1;
      if (startAt == 0) {
         day = n.getDate() + 1;
      }
      else {
         day = n.getDate();
      }
 
      a = Math.floor((14-month) / 12);
      y = year + 4800 - a;
      m = month + 12 * a - 3;
      b = Math.floor(y/4) - Math.floor(y/100) + Math.floor(y/400);
      J = day + Math.floor((153 * m + 2) / 5) + 365 * y + b - 32045;
      d4 = (((J + 31741 - (J % 7)) % 146097) % 36524) % 1461;
      L = Math.floor(d4 / 1460);
      d1 = ((d4 - L) % 365) + L;
      week = Math.floor(d1/7) + 1;
 
      return week;
   }
	
	function constructCalendar (dateNumField) {
		var aNumDays = Array (31,0,31,30,31,30,31,31,30,31,30,31)

		var dateMessage
		var	startDate =	new	Date (yearSelected,monthSelected,1)
		var endDate

		if (monthSelected==1)
		{
			endDate	= new Date (yearSelected,monthSelected+1,1);
			endDate	= new Date (endDate	- (24*60*60*1000));
			numDaysInMonth = endDate.getDate()
		}
		else
		{
			numDaysInMonth = aNumDays[monthSelected];
		}

		datePointer	= 0
		dayPointer = startDate.getDay() - startAt
		
		if (dayPointer<0)
		{
			dayPointer = 6
		}

		sHTML =	"<table	 border=0 style='font-family:verdana;font-size:10px;'><tr>"

		if (showWeekNumber==1)
		{
			sHTML += "<td width=27><b>" + weekString + "</b></td><td width=1 rowspan=7 bgcolor='#d0d0d0' style='padding:0px'><img src='"+imgDir+"divider.gif' width=1></td>"
		}

		for	(i=0; i<7; i++)	{
			sHTML += "<td width='27' align='right' style='font-family:arial;font-size:11px;'><B>"+ dayName[i]+"</B></td>"
		}
		sHTML +="</tr><tr>"
		
		if (showWeekNumber==1)
		{
			sHTML += "<td align=right>" + WeekNbr(startDate) + "&nbsp;</td>"
		}

		for	( var i=1; i<=dayPointer;i++ )
		{
			sHTML += "<td>&nbsp;</td>"
		}
	
		for	( datePointer=1; datePointer<=numDaysInMonth; datePointer++ )
		{
			dayPointer++;
			sHTML += "<td align=right>"
			sStyle=styleAnchor
			if ((datePointer==odateSelected) &&	(monthSelected==omonthSelected)	&& (yearSelected==oyearSelected))
			{ sStyle+=styleLightBorder }

			sHint = ""
			for (k=0;k<HolidaysCounter;k++)
			{
				if ((parseInt(Holidays[k].d)==datePointer)&&(parseInt(Holidays[k].m)==(monthSelected+1)))
				{
					if ((parseInt(Holidays[k].y)==0)||((parseInt(Holidays[k].y)==yearSelected)&&(parseInt(Holidays[k].y)!=0)))
					{
						sStyle+="background-color:#FFDDDD;"
						sHint+=sHint==""?Holidays[k].desc:"\n"+Holidays[k].desc
					}
				}
			}

			var regexp= /\"/g
			sHint=sHint.replace(regexp,"&quot;")

			dateMessage = "onmousemove='window.status=\""+selectDateMessage.replace("[date]",constructDate(datePointer,monthSelected,yearSelected))+"\"' onmouseout='window.status=\"\"' "

			if (paramDateField != -1) {
				if ((datePointer==dateNow)&&(monthSelected==monthNow)&&(yearSelected==yearNow))
				{ sHTML += "<b><a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer+";closeCalendar();checkWorkingDay("+paramDateField+");'><font color=#ff0000>&nbsp;" + datePointer + "</font>&nbsp;</a></b>"}
				else if	(dayPointer % 7 == (startAt * -1)+1)
				{ sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer + ";closeCalendar();checkWorkingDay("+paramDateField+");'>&nbsp;<font color=#909090>" + datePointer + "</font>&nbsp;</a>" }
				else
				{ sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer + ";closeCalendar();checkWorkingDay("+paramDateField+");'>&nbsp;" + datePointer + "&nbsp;</a>" }
			} else {
				if ((datePointer==dateNow)&&(monthSelected==monthNow)&&(yearSelected==yearNow))
				{ sHTML += "<b><a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer+";closeCalendar();'><font color=#ff0000>&nbsp;" + datePointer + "</font>&nbsp;</a></b>"}
				else if	(dayPointer % 7 == (startAt * -1)+1)
				{ sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer + ";closeCalendar();'>&nbsp;<font color=#909090>" + datePointer + "</font>&nbsp;</a>" }
				else
				{ sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer + ";closeCalendar();'>&nbsp;" + datePointer + "&nbsp;</a>" }			
			}

			sHTML += ""
			if ((dayPointer+startAt) % 7 == startAt) { 
				sHTML += "</tr><tr>" 
				if ((showWeekNumber==1)&&(datePointer<numDaysInMonth))
				{
					sHTML += "<td align=right>" + (WeekNbr(new Date(yearSelected,monthSelected,datePointer+1))) + "&nbsp;</td>"
				}
			}
		}

		document.getElementById("content").innerHTML   = sHTML
		document.getElementById("spanMonth").innerHTML = "&nbsp;" +	monthName[monthSelected] + "&nbsp;<IMG id='changeMonth' SRC='"+imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0>"
		
		
		yearSelected+= 543; //TODO:constructCalendar() year display
		document.getElementById("spanYear").innerHTML =	"&nbsp;" + (yearSelected)+ "&nbsp;<IMG id='changeYear' SRC='"+imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0>"
		yearSelected-= 543; //TODO:constructCalendar() set back
	}

	function popUpCalendar4Len(ctl,	ctl2, format, dayDObj, monthMObj, yearYObj,yLenObj,mLenObj, posi) {
		yearLen = yLenObj;
		monthLen = mLenObj;
		popUpCalendarRight(ctl,	ctl2, format, dayDObj, monthMObj, yearYObj, posi) ;
	}

	function popUpCalendarRight(ctl,	ctl2, format, dayDObj, monthMObj, yearYObj, posi) {
		var	leftpos=0
		var	toppos=0
	    var	rightpos=0

		if (bPageLoaded)
		{
			if ( crossobj.visibility ==	"hidden" ) {
				ctlToPlaceValue	= ctl2
				dateFormat = format
		        ctlDay = dayDObj
		        ctlMonth = monthMObj
		        ctlYear = yearYObj

				formatChar = " "
				aFormat	= dateFormat.split(formatChar)
				if (aFormat.length<3)
				{
					formatChar = "/"
					aFormat	= dateFormat.split(formatChar)
					if (aFormat.length<3)
					{
						formatChar = "."
						aFormat	= dateFormat.split(formatChar)
						if (aFormat.length<3)
						{
							formatChar = "-"
							aFormat	= dateFormat.split(formatChar)
							if (aFormat.length<3)
							{
								// invalid date	format
								formatChar=""
							}
						}
					}
				}

				tokensChanged =	0
				if ( formatChar	!= "" )
				{
					// use user's date
					aData =	ctl2.value.split(formatChar)

					for	(i=0;i<3;i++)
					{
						if ((aFormat[i]=="d") || (aFormat[i]=="dd"))
						{
							dateSelected = parseInt(aData[i], 10)
							tokensChanged ++
						}
						else if	((aFormat[i]=="m") || (aFormat[i]=="mm"))
						{
							monthSelected =	parseInt(aData[i], 10) - 1
							tokensChanged ++
						}
						else if	(aFormat[i]=="yyyy")
						{
							yearSelected = parseInt(aData[i], 10)
							// another palace for change D.C. to A.C.
							//yearSelected = yearSelected - 0
							yearSelected = yearSelected - 543
							tokensChanged ++
						}
						else if	(aFormat[i]=="mmm")
						{
							for	(j=0; j<12;	j++)
							{
								if (aData[i]==monthName[j])
								{
									monthSelected=j

									tokensChanged ++
								}
							}
						}
					}
				}

				if ((tokensChanged!=3)||isNaN(dateSelected)||isNaN(monthSelected)||isNaN(yearSelected))
				{
					dateSelected = dateNow
					monthSelected =	monthNow
					yearSelected = yearNow
				}

				odateSelected=dateSelected
				omonthSelected=monthSelected
				oyearSelected=yearSelected

				aTag = ctl
				do {
					aTag = aTag.offsetParent;
					leftpos	+= aTag.offsetLeft;
					toppos += aTag.offsetTop;
				} while(aTag.tagName!="BODY");

		        if (posi=="left") {
		            crossobj.left =	fixedX==-1 ? ctl.offsetLeft	- (ctl.offsetWidth + 193) + leftpos :	fixedX
					crossobj.top = fixedY==-1 ?	ctl.offsetTop +	toppos + ctl.offsetHeight + 5 :	fixedY
				} else if(posi=="left-top") {
		            crossobj.left =	fixedX==-1 ? ctl.offsetLeft	- (ctl.offsetWidth + 200) + leftpos :	fixedX
					crossobj.top = fixedY==-1 ?	ctl.offsetTop -	(toppos+180) + ctl.offsetHeight +	5 :	fixedY
				} else if(posi=="right-top") {
					crossobj.left =	fixedX==-1 ? ctl.offsetLeft	+ leftpos :	fixedX
					crossobj.top = fixedY==-1 ?	ctl.offsetTop -	(toppos+180) + ctl.offsetHeight +	5 :	fixedY
		        } else {      
					crossobj.left =	fixedX==-1 ? ctl.offsetLeft	+ leftpos :	fixedX
					crossobj.top = fixedY==-1 ?	ctl.offsetTop +	toppos + ctl.offsetHeight +	5 :	fixedY
			
					//crossobj.left =	fixedX==-1 ? ctl.offsetLeft	- (ctl.offsetWidth + 200) + leftpos :	fixedX
					//crossobj.top = fixedY==-1 ?	ctl.offsetTop +	toppos + ctl.offsetHeight +	2 :	fixedY
		        }
		        paramDateField = -1;
				constructCalendar (1, monthSelected, yearSelected);
				crossobj.visibility= "visible"
				document.getElementById("calendar").children[0].style.display = '';
				//hideElement( 'SELECT', document.getElementById("calendar") );
				//hideElement( 'APPLET', document.getElementById("calendar") );			

				bShow = true;
			}
			else
			{
				hideCalendar()
				if (ctlNow!=ctl) {popUpCalendarRight(ctl, ctl2, format, dayDObj, monthMObj, yearYObj, posi)}
			}
			ctlNow = ctl
		}
	}
	
	function popUpCalendarAndCallCheckWorkingDay(ctl,	ctl2, format, dayDObj, monthMObj, yearYObj, posi, dateNumField) {
		var	leftpos=0
		var	toppos=0
    	var	rightpos=0

		if (bPageLoaded)
		{
			if ( crossobj.visibility ==	"hidden" ) {
				ctlToPlaceValue	= ctl2
				dateFormat = format
		        ctlDay = dayDObj
		        ctlMonth = monthMObj
		        ctlYear = yearYObj

				formatChar = " "
				aFormat	= dateFormat.split(formatChar)
				if (aFormat.length<3)
				{
					formatChar = "/"
					aFormat	= dateFormat.split(formatChar)
					if (aFormat.length<3)
					{
						formatChar = "."
						aFormat	= dateFormat.split(formatChar)
						if (aFormat.length<3)
						{
							formatChar = "-"
							aFormat	= dateFormat.split(formatChar)
							if (aFormat.length<3)
							{
								// invalid date	format
								formatChar=""
							}
						}
					}
				}

				tokensChanged =	0
				if ( formatChar	!= "" )
				{
					// use user's date
					aData =	ctl2.value.split(formatChar)

					for	(i=0;i<3;i++)
					{
						if ((aFormat[i]=="d") || (aFormat[i]=="dd"))
						{
							dateSelected = parseInt(aData[i], 10)
							tokensChanged ++
						}
						else if	((aFormat[i]=="m") || (aFormat[i]=="mm"))
						{
							monthSelected =	parseInt(aData[i], 10) - 1
							tokensChanged ++
						}
						else if	(aFormat[i]=="yyyy")
						{
							yearSelected = parseInt(aData[i], 10)
							// another palace for change D.C. to A.C.
							yearSelected = yearSelected - 0
							tokensChanged ++
						}
						else if	(aFormat[i]=="mmm")
						{
							for	(j=0; j<12;	j++)
							{
								if (aData[i]==monthName[j])
								{
									monthSelected=j

									tokensChanged ++
								}
							}
						}
					}
				}

				if ((tokensChanged!=3)||isNaN(dateSelected)||isNaN(monthSelected)||isNaN(yearSelected))
				{
					dateSelected = dateNow
					monthSelected =	monthNow
					yearSelected = yearNow
				}

				odateSelected=dateSelected
				omonthSelected=monthSelected
				oyearSelected=yearSelected

				aTag = ctl
				do {
					aTag = aTag.offsetParent;
					leftpos	+= aTag.offsetLeft;
					toppos += aTag.offsetTop;
				} while(aTag.tagName!="BODY");

		        if (posi=="left") {
		            crossobj.left =	fixedX==-1 ? ctl.offsetLeft	- (ctl.offsetWidth + 200) + leftpos :	fixedX
					crossobj.top = fixedY==-1 ?	ctl.offsetTop +	toppos + ctl.offsetHeight +	2 :	fixedY
				} else if(posi=="left-top") {
		            crossobj.left =	fixedX==-1 ? ctl.offsetLeft	- (ctl.offsetWidth + 200) + leftpos :	fixedX
					crossobj.top = fixedY==-1 ?	ctl.offsetTop -	(toppos+180) + ctl.offsetHeight +	2 :	fixedY
				} else if(posi=="right-top") {
					crossobj.left =	fixedX==-1 ? ctl.offsetLeft	+ leftpos :	fixedX
					crossobj.top = fixedY==-1 ?	ctl.offsetTop -	(toppos+180) + ctl.offsetHeight +	2 :	fixedY
		        } else {      
					crossobj.left =	fixedX==-1 ? ctl.offsetLeft	+ leftpos :	fixedX
					crossobj.top = fixedY==-1 ?	ctl.offsetTop +	toppos + ctl.offsetHeight +	2 :	fixedY
			
					//crossobj.left =	fixedX==-1 ? ctl.offsetLeft	- (ctl.offsetWidth + 200) + leftpos :	fixedX
					//crossobj.top = fixedY==-1 ?	ctl.offsetTop +	toppos + ctl.offsetHeight +	2 :	fixedY
		        }
		        paramDateField = dateNumField;
				constructCalendar ();
				crossobj.visibility= "visible" ;

				hideElement( 'SELECT', document.getElementById("calendar") );
				hideElement( 'APPLET', document.getElementById("calendar") );			

				bShow = true;
			}
			else
			{
				hideCalendar()
				if (ctlNow!=ctl) {popUpCalendarAndCallCheckWorkingDay(ctl, ctl2, format, dayDObj, monthMObj, yearYObj, posi, dateNumField)}
			}
			ctlNow = ctl
		}
		
	}		

	document.onkeypress = function hidecal1 () { 
		if (event.keyCode==27) 
		{
			hideCalendar()
		}
	}
	document.onclick = function hidecal2 () { 		
		if (!bShow)
		{
			hideCalendar()
		}
		bShow = false
	}

	init()

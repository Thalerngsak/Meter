/* Licence:
 *   Use this however/wherever you like, just don't blame me if it breaks anything.
 *
 * Credit:
 *   If you're nice, you'll leave this bit:
 *
 *   Class by Pierre-Alexandre Losson -- http://www.telio.be/blog
 *   email : plosson@users.sourceforge.net
 */

/*
 *  Changed for Part 2, by Ken Cochrane
 *  http://KenCochrane.net , http://CampRate.com , http://PopcornMonsters.com
 */
function refreshProgress() {
	ProgressMonitor.getProgressInfo(updateProgress);
}

function updateProgress(ProgressInfo) {

	var progressPercent = Math.ceil(ProgressInfo.progress);

	document.getElementById('progressBarText').innerHTML = '' + progressPercent + '%';

	document.getElementById('progressBarBoxContent').style.width = parseInt(progressPercent * 3.5) + 'px';
	if (ProgressInfo.progress < 100) {
		window.setTimeout('refreshProgress()', 1000);
	}

	return true;
}
function updateStatusMessage(message) {
	DWRUtil.setValue('updateStatusMsg', message);
}
function startProgress() {
	//alert(enable);
	if (enableProgressbar) { // enable progressbar from progressBar.jsp?
		var init = 3;
		updateStatusMessage("");
		document.getElementById('progressBar').style.display = 'block';
		document.getElementById('progressBarText').innerHTML = init+'%';
		document.getElementById('progressBarBoxContent').style.width = parseInt(init * 3.5) + 'px';
		// wait a little while to make sure the Progress has started ..
		window.setTimeout("refreshProgress()", 1500);
	}
	return true;
}

function hideProgressBar() {
	document.getElementById('progressBar').style.display = 'none';
	document.getElementById('progressBarText').innerHTML = '';
}

// function ProgressMonitor() { }
// ProgressMonitor._path = '';
// ProgressMonitor.getProgressInfo = function(callback) {
// DWREngine._execute(ProgressMonitor._path, 'ProgressMonitor',
// 'getProgressInfo', callback);
// }

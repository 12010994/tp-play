/**
 * @param log The log root element
 */
window.observeJourney = function (log) {
  // Get the notifications stream as an event source
  var notifications = new EventSource(routes.controllers.Journeys.attendees(log.dataset.journeyId).url);
  // Process notifications by adding a new element to the log
  notifications.onmessage = function (m) {
    var li = document.createElement('li');
    li.textContent = JSON.parse(m.data).name + ' attended to the event';
    log.appendChild(li);
  };
};

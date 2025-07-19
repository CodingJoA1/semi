document.addEventListener('DOMContentLoaded', function () {
  const calendarEl = document.getElementById('calendar');
  const colors = ['#007bff', '#28a745', '#ffc107', '#dc3545', '#6610f2'];
  let colorIndex = 0;

  const calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,listMonth'
    },
    events: [], // 일정 비어 있음 (초기값)

    // 날짜 클릭 시 일정 추가
    dateClick: function(info) {
      const title = prompt(info.dateStr + ' 일정 제목을 입력하세요:');
      if (!title) return;

      const isOneDay = confirm("이 일정은 하루짜리인가요?");
      let endDate = null;

      if (!isOneDay) {
        endDate = prompt('종료 날짜를 YYYY-MM-DD 형식으로 입력하세요:');
        if (!endDate) return;
      }

      // D-day 계산
      const today = new Date();
      const startDate = new Date(info.dateStr);
      const timeDiff = startDate - today;
      const daysLeft = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));

      const color = colors[colorIndex];
      colorIndex = (colorIndex + 1) % colors.length;

      calendar.addEvent({
        title: `${title} (${daysLeft >= 0 ? 'D-' + daysLeft : '마감됨'})`,
        start: info.dateStr,
        end: endDate || undefined,
        allDay: true,
        color: color
      });

      alert('일정이 추가되었습니다.');
    },

    // 일정 클릭 시 삭제
    eventClick: function (info) {
      if (confirm(`"${info.event.title}" 일정을 삭제하시겠습니까?`)) {
        info.event.remove();
        alert('일정이 삭제되었습니다.');
      }
    }
  });

  calendar.render();
});
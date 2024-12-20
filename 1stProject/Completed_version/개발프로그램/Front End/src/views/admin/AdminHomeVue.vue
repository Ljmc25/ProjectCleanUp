<template>
  <div>
    <h3>Welcome! Admin</h3>
    <hr />
    <br />
    <br />

    <h3>전체 주문 현황</h3>
    <table class="table" style="width:70vw;">
      <thead>
        <tr class="table-secondary">
          <th scope="col"></th>
          <th scope="col">총 주문건수</th>
          <th scope="col">총 주문액</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row"></th>
          <td>40</td>
          <td>2,623,600</td>
        </tr>
      </tbody>
    </table>
    <br />
    <br />

    <!-- 달력과 계산기 영역 -->
    <div class="flex-container">
      <!-- 달력 -->
      <div class="calendar">
        <h4>달력</h4>
        <div class="calendar-container">
          <div class="calendar-header">
            <button class="month-button" @click="prevMonth">◀</button>
            <span>{{ currentMonth }}</span>
            <button class="month-button" @click="nextMonth">▶</button>
          </div>
          <div class="calendar-days">
            <div
              v-for="(day, index) in daysOfMonth"
              :key="index"
              class="calendar-day"
              :style="getDayStyle(day)"
              @click="selectDay(day)"
            >
              {{ day }}
            </div>
          </div>
        </div>
      </div>

      <!-- 메모 리스트 -->
      <div class="memo-list">
        <h4>메모 목록</h4>
        <ul>
          <li v-for="(notes, day) in getNotesForCurrentMonth" :key="day">
            <strong>{{ day }}일:</strong>
            <ul>
              <li v-for="(note, index) in notes" :key="index">
                <div class="note" @click="editNote(day, index)">
                  {{ note }}
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>

    <!-- 계산기 -->
    <div class="calculator">
      <h4>계산기</h4>
      <div class="calculator-container">
        <input
          type="text"
          v-model="display"
          disabled
          class="display"
          placeholder="0"
        />
        <div class="buttons">
          <button @click="appendToDisplay('1')">1</button>
          <button @click="appendToDisplay('2')">2</button>
          <button @click="appendToDisplay('3')">3</button>
          <button @click="appendToDisplay('+')">+</button>
          <button @click="appendToDisplay('4')">4</button>
          <button @click="appendToDisplay('5')">5</button>
          <button @click="appendToDisplay('6')">6</button>
          <button @click="appendToDisplay('-')">-</button>
          <button @click="appendToDisplay('7')">7</button>
          <button @click="appendToDisplay('8')">8</button>
          <button @click="appendToDisplay('9')">9</button>
          <button @click="appendToDisplay('*')">*</button>
          <button @click="appendToDisplay('0')">0</button>
          <button @click="clearDisplay()">C</button>
          <button @click="calculate()">=</button>
          <button @click="appendToDisplay('/')">/</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentDate: new Date(),
      display: '',
      notes: this.loadNotes(), // 로컬스토리지에서 메모 불러오기
      selectedDay: null, // 클릭한 날짜
      selectedNoteIndex: null, // 클릭한 메모의 인덱스
    };
  },
  computed: {
    currentMonth() {
      const monthNames = [
        "1월", "2월", "3월", "4월", "5월", "6월",
        "7월", "8월", "9월", "10월", "11월", "12월"
      ];
      return `${monthNames[this.currentDate.getMonth()]} ${this.currentDate.getFullYear()}`;
    },
    daysOfMonth() {
      const daysInMonth = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth() + 1, 0).getDate();
      const days = [];
      for (let i = 1; i <= daysInMonth; i++) {
        days.push(i);
      }
      return days;
    },
    // 현재 달의 모든 날짜별 메모 가져오기
    getNotesForCurrentMonth() {
      const yearMonth = `${this.currentDate.getFullYear()}-${this.currentDate.getMonth() + 1}`;
      return this.notes[yearMonth] || {};
    },
  },
  methods: {
    prevMonth() {
      const newDate = new Date(this.currentDate);
      newDate.setMonth(this.currentDate.getMonth() - 1);
      this.currentDate = newDate;
    },
    nextMonth() {
      const newDate = new Date(this.currentDate);
      newDate.setMonth(this.currentDate.getMonth() + 1);
      this.currentDate = newDate;
    },
    selectDay(day) {
      this.selectedDay = day;
      const existingNotes = this.getNotesForDay(day);
      const newNote = prompt(
        `날짜 ${day}에 대한 메모를 입력하세요.`,
        existingNotes.join('\n') || ''
      );

      if (newNote === null) return;

      const notesArray = newNote.trim().split('\n');
      if (notesArray.length === 0 || notesArray[0] === '') {
        this.deleteNotesForDay(day);
      } else {
        this.saveNotesForDay(day, notesArray);
      }
    },
    getNotesForDay(day) {
      const yearMonth = `${this.currentDate.getFullYear()}-${this.currentDate.getMonth() + 1}`;
      return this.notes[yearMonth]?.[day] || [];
    },
    saveNotesForDay(day, notesArray) {
      const yearMonth = `${this.currentDate.getFullYear()}-${this.currentDate.getMonth() + 1}`;
      if (!this.notes[yearMonth]) {
        this.notes[yearMonth] = {};
      }
      this.notes[yearMonth][day] = notesArray;
      this.saveNotes();
    },
    deleteNotesForDay(day) {
      const yearMonth = `${this.currentDate.getFullYear()}-${this.currentDate.getMonth() + 1}`;
      if (this.notes[yearMonth]) {
        delete this.notes[yearMonth][day];
      }
      this.saveNotes();
    },
    saveNotes() {
      localStorage.setItem('notes', JSON.stringify(this.notes));
    },
    loadNotes() {
      return JSON.parse(localStorage.getItem('notes')) || {};
    },
    appendToDisplay(value) {
      this.display += value;
    },
    clearDisplay() {
      this.display = '';
    },
    calculate() {
      try {
        this.display = eval(this.display).toString();
      } catch (error) {
        this.display = 'Error';
      }
    },
    editNote(day, index) {
      const existingNotes = this.getNotesForDay(day);
      const updatedNote = prompt(
        `날짜 ${day}에 대한 메모를 수정하세요.`,
        existingNotes[index]
      );

      if (updatedNote === null) return;

      existingNotes[index] = updatedNote.trim();
      this.saveNotesForDay(day, existingNotes);
    },
    getDayStyle(day) {
      const dayOfWeek = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), day).getDay();
      const isSaturday = dayOfWeek === 6; // 토요일
      const isSunday = dayOfWeek === 0; // 일요일

      return {
        color: isSaturday ? 'blue' : isSunday ? 'red' : 'black',
        backgroundColor: this.isNoteExist(day) ? 'yellow' : 'white',
      };
    },
    isNoteExist(day) {
      const yearMonth = `${this.currentDate.getFullYear()}-${this.currentDate.getMonth() + 1}`;
      return this.notes[yearMonth]?.[day]?.length > 0;
    },
  },
};
</script>

<style scope>
/* 기본 스타일 */
table {
  width: 70%;
  margin-bottom: 30px;
}

/* 달력 스타일 */
.flex-container {
  display: flex;
  justify-content: space-between;
  margin-top: 30px;
  margin-bottom: 5vh;
  margin-right: 10vw;
  gap: 1rem;
}

.calendar {
  width: 100%; /* 크기 조정 */
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.calendar-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.calendar-header {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

.month-button {
  font-size: 1.2rem;
  cursor: pointer;
  border: none;
  background: transparent;
}

.calendar-days {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 5px;
  margin-top: 10px;
}

.calendar-day {
  padding: 10px;
  text-align: center;
  border: 1px solid #ddd;
  cursor: pointer;
  background-color: white;
}

.memo-list {
  width: 100%; /* 크기 조정 */
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
  margin-top: 20px;
}

.memo-list ul {
  list-style-type: none;
  padding: 0;
}

.memo-list li {
  margin-bottom: 10px;
}

.note {
  padding: 5px;
  cursor: pointer;
  background-color: #f0f0f0;
  border-radius: 4px;
}

.note:hover {
  background-color: #ddd;
}

/* 계산기 스타일 */
.calculator {
  width: 45%; /* 크기 축소 */
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.calculator-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.display {
  width: 100%;
  padding: 10px;
  font-size: 1.2rem;
  text-align: right;
  border: 1px solid #ddd;
  margin-bottom: 10px;
}

.buttons {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 5px;
  width: 100%;
}

button {
  padding: 10px;
  font-size: 1.2rem;
  cursor: pointer;
  border: 1px solid #ddd;
}

button:hover {
  background-color: #f0f0f0;
}
</style>

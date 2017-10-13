import React, { Component } from 'react'

import MealRow from './MealRow.js'

class ScheduleEat extends Component {
  constructor() {
    super()
    this.state = {
      meals: []
    }
    this.selectDay = this.selectDay.bind(this)
  }
  selectDay(evt) {
    evt.preventDefault()
    evt.stopPropagation()
    //ajax request for that day's meals
  }
  render() {
    return (
      <div id="scheduleEat">
        <div id="weekView">
          <div
            onClick={this.selectDay}
            className="weekBtn">
            Sunday
          </div>
          <div
            onClick={this.selectDay}
            className="weekBtn">
            Monday
          </div>
          <div
            onClick={this.selectDay}
            className="weekBtn">
            Tuesday
          </div>
          <div
            onClick={this.selectDay}
            className="weekBtn">
            Wednesday
          </div>
          <div
            onClick={this.selectDay}
            className="weekBtn">
            Thursday
          </div>
          <div
            onClick={this.selectDay}
            className="weekBtn">
            Friday
          </div>
          <div
            onClick={this.selectDay}
            className="weekBtn">
            Saturday
          </div>
        </div>
        <div id="meals">
        {
          this.state.meals.length > 0 ?
          this.state.meals.map(meal => <MealRow meal={meal} />) :
          null
        }
        </div>
      </div>
    )
  }
}

export default ScheduleEat

import React, { Component } from 'react'

import MealRow from './MealRow.js'

const fakeMeals = [
  {
    pickUp: true,
    sitDown: false,
    chef: {
      name: 'John Smith'
    },
    recipe: {
      title: 'Apple Pie',
      description: 'Scrumptious, definitely healthy'
    }
  }, {
    pickUp: true,
    sitDown: false,
    chef: {
      name: 'Jane Doe'
    },
    recipe: {
      title: 'Chicken Salad',
      description: 'With apples, definitely healthy!'
    }
  }
]


class ScheduleEat extends Component {
  constructor() {
    super()
    this.state = {
      meals: fakeMeals //hard-coded for fake meals currently
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

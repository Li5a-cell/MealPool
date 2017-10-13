import React, { Component } from 'react'
import axios from 'axios'

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
    //hard-coded for dates this week
    axios.get('/api/schedule', evt.target.name) //date format: "MM-DD-YYYY"
      .then(res => res.data)
      .then(data => {
        this.setState({
          meals: data
        })
      })
  }
  render() {
    return (
      <div id="scheduleEat">
        <div id="weekView">
          <button
            name="10-15-2017"
            onClick={this.selectDay}
            className="weekBtn">
            Sunday
          </button>
          <button
            name="10-16-2017"
            onClick={this.selectDay}
            className="weekBtn">
            Monday
          </button>
          <button
            name="10-17-2017"
            onClick={this.selectDay}
            className="weekBtn">
            Tuesday
          </button>
          <button
            name="10-18-2017"
            onClick={this.selectDay}
            className="weekBtn">
            Wednesday
          </button>
          <button
            name="10-19-2017"
            onClick={this.selectDay}
            className="weekBtn">
            Thursday
          </button>
          <button
            name="10-20-2017"
            onClick={this.selectDay}
            className="weekBtn">
            Friday
          </button>
          <button
            name="10-21-2017"
            onClick={this.selectDay}
            className="weekBtn">
            Saturday
          </button>
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

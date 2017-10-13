import React, { Component } from 'react'

import MealRow from './MealRow.js'

class ScheduleCook extends Component {
  constructor() {
    super()
    this.state = {
      meals: []
    }
    this.selectDay = this.selectDay.bind(this)
  }
  componentDidMount() {
    //ajax request for the user's recipes for that week -- sets to empties if not present
    this.setState({

    })
  }
  selectDay(evt) {
    evt.preventDefault()
    evt.stopPropagation()
    //ajax request for that day's meals
  }
  render() {
    return (
      <div id="ScheduleCook">
        <div id="weekView">
          <button
            onClick={this.selectDay}
            className="weekBtn">
            Sunday
          </button>
          <button
            onClick={this.selectDay}
            className="weekBtn">
            Monday
          </button>
          <button
            onClick={this.selectDay}
            className="weekBtn">
            Tuesday
          </button>
          <button
            onClick={this.selectDay}
            className="weekBtn">
            Wednesday
          </button>
          <button
            onClick={this.selectDay}
            className="weekBtn">
            Thursday
          </button>
          <button
            onClick={this.selectDay}
            className="weekBtn">
            Friday
          </button>
          <button
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

export default ScheduleCook

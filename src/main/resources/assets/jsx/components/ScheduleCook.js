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

export default ScheduleCook

import React, { Component } from 'react'
import axios from 'axios'

class MealRow extends Component {
  constructor() {
    super()
    this.state = {

    }
    this.requestMeal = this.requestMeal.bind(this)
  }
  requestMeal(evt) {
    evt.preventDefault()
    evt.stopPropagation()
    axios.post('/api/user/schedule', {scheduleId: 1})
  }
  render() {
    const meal = this.props.meal.recipe
    return (
      <div className="mealRow">
        <img src={meal.photo} />
        <h4>{meal.title}</h4>
        <p>{meal.description}</p>
        <div className="requestBtn">
          <button
            onClick={this.requestMeal}
          >
            REQUEST MEAL
          </button>
        </div>
      </div>
    )
  }
}

export default MealRow

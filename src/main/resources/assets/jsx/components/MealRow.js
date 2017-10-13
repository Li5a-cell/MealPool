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
    console.log('hello')
    console.log(this.props.meal.date)
    axios.post('/api/schedule', {
        name: this.props.meal.recipe.title,
        description: this.props.meal.recipe.description,
        photo: this.props.meal.date
    })
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

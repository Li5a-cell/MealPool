import React, { Component } from 'react'

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
    //ajax request
  }
  render() {
    const meal = this.props.meal
    return (
      <div className="mealRow">
        <img src={meal.photo} />
        <h4>{meal.title}</h4>
        <div className="requestBtn">
          <button
            onClick={this.requestMeal}
          >
          </button>
        </div>
      </div>
    )
  }
}

export default MealRow

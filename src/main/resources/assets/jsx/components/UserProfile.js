import React, { Component } from 'react'

class UserProfile extends Component {
  constructor() {
    super()
    this.state = {
      mealsToCook: [],
      mealsToEat: []
    }
    this.verify = this.verify.bind(this)
    this.fail = this.fail.bind(this)
  }
  componentDidMount() {
    //ajax request for user's meals to cook and to eat
  }
  verify(evt, id) {
    evt.preventDefault()
    evt.stopPropagation()
    //ajax request to submit verification
  }
  fail(evt, id) {
    evt.preventDefault()
    evt.stopPropagation()
    //ajax request to submit a failure
  }
  render() {
    return (
      <div id="userProfile">

        <div className="mealsList">
          Meals to Pickup
          {
            this.state.mealsToPickup.length > 0 ?
              this.state.mealsToPickup.map(meal => meal.title) : null
          }
        </div>
        <div className="mealsList">
          Meals to Cook
          {
            this.state.mealsToCook.length > 0 ?
              this.state.mealsToCook.map(meal => (
                <div className="mealVerifyRow">
                  Did {this.meal.user} pick up their healthy meal?
              <button
                    onClick={this.verify(evt, this.meal.id)}
                  >
                    Yes
              </button>
                  <button
                    onClick={this.fail(evt, this.meal.id)}
                  >
                    No
              </button>
                </div>
              )) : null
          }
        </div>
      </div>
    )
  }
}

export default UserProfile

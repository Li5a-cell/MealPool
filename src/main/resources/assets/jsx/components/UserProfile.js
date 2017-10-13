import React, { Component } from 'react'
import axios from 'axios'

class UserProfile extends Component {

  constructor() {
    super()
    this.state = {
      mealsToCook: [],
      mealsToEat: []
    }
    this.verify = this.verify.bind(this)
    this.fail = this.fail.bind(this)
    var me = this;
    axios.get('/api/user')
        .then(function (response) {
            me.state = {
                mealsToCook: [],
                mealsToEat: response.data.eatingEvents[0].recipe
            }
        })
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
        <h1>{this.props.user.name}</h1>
        <div className="mealsList">
          Meals to Pickup
          {
            this.state.mealsToEat.length > 0 ?
              this.state.mealsToEat.map(meal => meal.name) : null
          }
        </div>
        <div className="mealsList">
          Meals to Cook
          {
            this.state.mealsToCook.length > 0 ?
              this.state.mealsToCook.map(meal => (
                <div className="mealVerifyRow">
                  Did {meal.user} pick up their healthy meal?
              <button
                    onClick={this.verify(evt, meal.id)}
                  >
                    Yes
              </button>
                  <button
                    onClick={this.fail(evt, meal.id)}
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

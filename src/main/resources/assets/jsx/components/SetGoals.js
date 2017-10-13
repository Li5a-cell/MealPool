import React, { Component } from 'react'

class SetGoals extends Component {
  constructor() {
    super()
    this.state = {
      cookGoals: 0,
      eatGoals: 0,
      cookMeals: 0,
      eatMeals: 0
    }
  }
  submitGoals(evt) {
    evt.preventDefault()
    evt.stopPropagation()
    //ajax request with goals
  }
  handleFormChange(evt) {
    evt.preventDefault()
    this.setState({
      [evt.target.name]: evt.target.value
    })
  }
  render() {
    return (
      <div id="goals">
        <div id="setGoals">
          Set your current goals.
        <div id="setGoalsForm">
            <form>
              Cook:
            <input
                name="cookGoals"
                id="setGoalsCook"
                type="number"
                value={this.state.cookGoals}
                min="0"
                max="21"
                onChange={this.handleFormChange}
              />
              Eat:
            <input
                name="eatGoals"
                id="setGoalsEat"
                type="number"
                value={this.state.eatGoals}
                min="0"
                max="21"
                onChange={this.handleFormChange}
              />
            </form>
          </div>
          <div id="setGoalsBtns">
            <button
              onClick={this.submitGoals}
            >
            </button>
          </div>
          <hr />
        </div>
        <div id="runningGoals">
          Your progress:
          { this.state.eatGoals > 0 ?
            <div>You have eaten {this.state.eatMeals} / {this.state.eatGoals} meals.</div> : null }
          { this.state.cookGoals > 0 ?
            <div>You have cooked {this.state.cookMeals} / {this.state.cookGoals} meals.</div> : null }
        </div>
      </div>
    )
  }
}

export default SetGoals

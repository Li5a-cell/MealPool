import React, { Component } from 'react'
import axios from 'axios'

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
  submitRecipe(evt) {
    evt.preventDefault()
      evt.stopPropagation()
      //console.log(this.refs.recipe.value)
      //const body = {}
      //axios.post('/api/schedule', )
  }
  render() {
    return (
        <div id="ScheduleCook">
          <div id="cookForm">
            <form onSubmit={this.submitRecipe}>
              Date:
              <select ref="cookDate">
                <option value="Sunday">Sunday (10-15-2017)</option>
                <option value="Monday">Monday (10-16-2017)</option>
                <option value="Tuesday">Tuesday (10-17-2017)</option>
                <option value="Wednesday">Wednesday (10-18-2017)</option>
                <option value="Thursday">Thursday (10-19-2017)</option>
                <option value="Friday">Friday (10-20-2017)</option>
                <option value="Saturday">Saturday (10-21-2017)</option>
              </select><br/>
              Recipe: <input ref="recipe" type="text" placeholder="Enter Recipe Name" ></input><br/>
              Description: <input ref="description" type="text" placeholder="Enter a Description" ></input><br/>
              Max Orders: <input ref="maxOrders" type="number" ></input><br/>
              Price: <input ref="price" type="number" ></input><br/>
              <input type="submit" value="Submit Recipe" ></input>
            </form>
          </div>
        </div>
    )
  }
}

export default ScheduleCook

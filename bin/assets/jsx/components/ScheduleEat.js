import React, { Component } from 'react'
import axios from 'axios'

import MealRow from './MealRow.js'

const sunMeals = [
  {
    pickUp: true,
    sitDown: false,
    chef: {
      name: 'John Smith'
    },
    recipe: {
      title: 'Apple Pie',
      description: 'Scrumptious, made with fresh apples'
    },
    date: 'Sunday (10-15-2017)'
  }, {
    pickUp: true,
    sitDown: false,
    chef: {
      name: 'Jane Doe'
    },
    recipe: {
      title: 'Chicken Salad',
      description: 'Beautiful organic chicken salad with homemade dressing'
    },
    date: 'Sunday (10-15-2017)'
  }
]

const monMeals = [
    {
        pickUp: true,
        sitDown: false,
        chef: {
            name: 'Lisa'
        },
        recipe: {
            title: 'Fish Tacos',
            description: 'Made with fresh cod, pico, and red onion'
        },
        date: 'Monday (10-16-2017)'
    }
]

const tueMeals = [
    {
        pickUp: true,
        sitDown: false,
        chef: {
            name: 'Ryan'
        },
        recipe: {
            title: 'Roasted Chicken and Vegetables',
            description: 'Organic chicken legs, baked with carrots and potatoes!'
        },
        date: 'Saturday (10-21-2017)'
    }
]

const wedMeals = [
    {
        pickUp: true,
        sitDown: false,
        chef: {
            name: 'Emily'
        },
        recipe: {
            title: 'Beef Stirfry',
            description: 'Beef stir fry with assorted vegetables'
        },
        date: 'Wednesday (10-18-2017)'
    }, {
        pickUp: true,
        sitDown: false,
        chef: {
            name: 'Ryan'
        },
        recipe: {
            title: 'Chicken Noodle Soup',
            description: 'Made with chicken breast, carrots, and celery'
        },
        date: 'Wednesday (10-18-2017)'
    }
]

const thuMeals = [
    {
        pickUp: true,
        sitDown: false,
        chef: {
            name: 'Lisa'
        },
        recipe: {
            title: 'Chili',
            description: 'Spicy chili with ground beef, beans, and peppers'
        },
        date: 'Thursday (10-19-2017)'
    }
]

const friMeals = [
    {
        pickUp: true,
        sitDown: false,
        chef: {
            name: 'John Smith'
        },
        recipe: {
            title: 'Cherry Pie',
            description: 'Scrumptious, made with fresh cherries'
        },
        date: 'Friday (10-20-2017)'
    }
]

const satMeals = [
    {
        pickUp: true,
        sitDown: false,
        chef: {
            name: 'Chef'
        },
        recipe: {
            title: 'Chicken, rice, and green beans',
            description: 'Sauteed chicken, delicious green beans and creamy mushroom soup'
        },
        date: 'Saturday (10-21-2017)'
    }
]

class ScheduleEat extends Component {
  constructor() {
    super()
    this.state = {
      meals: sunMeals //hard-coded for fake meals currently
    }
    this.selectDay = this.selectDay.bind(this)
  }
  selectDay(evt) {
    evt.preventDefault()
    evt.stopPropagation()
    //hard-coded for dates this week
    switch(evt.target.name) {
        case "10-15-2017":
          this.setState({
              meals: sunMeals
          })
          break;
        case "10-16-2017":
            this.setState({
                meals: monMeals
            })
          break;
        case "10-17-2017":
            this.setState({
                meals: tueMeals
            })
          break;
        case "10-18-2017":
            this.setState({
                meals: wedMeals
            })
          break;
        case "10-19-2017":
            this.setState({
                meals: thuMeals
            })
          break;
        case "10-20-2017":
            this.setState({
                meals: friMeals
            })
          break;
        case "10-21-2017":
            this.setState({
                meals: satMeals
            })
          break;
    }
      /*
    axios.get('/api/schedule', evt.target.name) //date format: "MM-DD-YYYY"
      .then(res => res.data)
      .then(data => {
        this.setState({
          meals: data
        })
      })
      */
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

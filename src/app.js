import { Component } from 'react'
import store from './store/toolkitIndex'
import { Provider } from 'react-redux'
import './app.scss'
/* app.js */
import './custom-variables.scss'

class App extends Component {

  componentDidMount () {}

  componentDidShow () {}

  componentDidHide () {}

  // this.props.children 是将要会渲染的页面
  render () {
    // return this.props.children
    return (
      <Provider store={store}>{this.props.children}</Provider>
    )
  }
}

export default App

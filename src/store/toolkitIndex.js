import { createSlice, configureStore } from "@reduxjs/toolkit";


/**
 * 创建答题题目
 */
let answerSlice = createSlice({
  name: "answerSlice",
  initialState: {
    answer: [],
  },
  reducers: {
    addAnswer(state, action) {
      if (!state.answer[action.payload.current]) {
        state.answer.push(action.payload)
      } else {
        state.answer[action.payload.current] = action.payload
      }

    },
    changeAnswer(state, action) {
      state.answer = action.payload
    }
  }
})

/**
 * 创建上传题目
 */
let uploadTopicSlice = createSlice({
  name: "uploadTopicSlice",
  initialState: {
    topic: []
  },
  reducers: {
    changeTopic(state, action) {
      if (!state.topic[action.payload.current]) {
        state.topic.push(action.payload)
      } else {
        state.topic[action.payload.current] = action.payload
      }
    }
  }
})



let mesSlice = createSlice({
  name: "mesSlice",
  initialState: {
    mes: "hello"
  },
  // 修改数据的行为
  reducers: {
    changeMes(state, action) {
      state.mes = action.payload
    }
  }
})


let numSlice = createSlice({
  name: "numSlice",
  initialState: {
    num: 0
  },
  reducers: {
    addNum(state, action) {
      state.num += 1
    }
  }
})

let store = configureStore({
  reducer: {
    mesReducer: mesSlice.reducer,
    numReducer: numSlice.reducer,
    answerReducer: answerSlice.reducer,
    uploadTopicReducer: uploadTopicSlice.reducer
  }
})

export default store
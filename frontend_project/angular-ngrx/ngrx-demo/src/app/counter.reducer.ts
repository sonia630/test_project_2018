import { createReducer, on } from '@ngrx/store';
import { increment, decrement, reset, helloSuccess } from './counter.actions';
import { State, TitleState } from './counter.selector';

export const initialState: State & TitleState = {
  count: 0,
  title: '',
};
const localCounterReducer = createReducer(initialState,
  on(increment, (state) => {
    console.log('incre....', state);
    const res = {...state, count: state.count + 1};
    return res;
  }),
  on(decrement, state => ({...state, count: state.count - 1})),
  on(reset, state => ({...state, count: 0})),
  on(helloSuccess, (state, { title } ) => {
     console.log('param title ....', title);
     return { ...state, title } ;
  })
);
export function counterReducer(state, action) {
  console.log('counter reducer...', state, action);
  return localCounterReducer(state, action);
};
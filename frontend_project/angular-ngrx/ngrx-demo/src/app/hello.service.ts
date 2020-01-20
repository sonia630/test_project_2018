import { Injectable } from '@angular/core';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HelloService {

  constructor() { }

  getHello(action) {
    console.log('service get hello....', action);
    return  of('hello world ngrx');
  }
}

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JasmineComponentComponent } from './jasmine-component.component';
import { DebugElement } from '@angular/core';

describe('JasmineComponentComponent', () => {
  let component: JasmineComponentComponent;
  let fixture: ComponentFixture<JasmineComponentComponent>;
  let de: DebugElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [JasmineComponentComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JasmineComponentComponent);
    component = fixture.componentInstance;
    de = fixture.debugElement;
    fixture.detectChanges();
  });

  afterEach(() => {});

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('test compute should return 0 if input is negative', () => {
    const result = component.compute(-1);
    expect(result).toBe(0);
  });

  it('should increment the input if it is posible', () => {
    const result = component.compute(1);
    expect(result).toBe(2);
  });

  it('should include the name in the message', () => {
    expect(component.greet('mosh')).toContain('mosh');
  });

  it('should return the supported currencies', () => {
    expect(component.arr()).toContain('usa');
    expect(component.arr()).toContain('aud');
  });

  it('should increment totalVotes when upvote', () => {
    component.upVote();
    expect(component.totalVotes).toBe(1);
  });

  it('should decrement totalVotes when downvote', () => {
    component.downVote();
    expect(component.totalVotes).toBe(-1);
  });
});

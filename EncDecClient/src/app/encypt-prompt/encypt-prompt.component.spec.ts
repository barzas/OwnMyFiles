import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EncyptPromptComponent } from './encypt-prompt.component';

describe('EncyptPromptComponent', () => {
  let component: EncyptPromptComponent;
  let fixture: ComponentFixture<EncyptPromptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EncyptPromptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EncyptPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

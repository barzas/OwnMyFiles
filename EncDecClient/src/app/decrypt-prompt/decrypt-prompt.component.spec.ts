import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DecryptPromptComponent } from './decrypt-prompt.component';

describe('DecryptPromptComponent', () => {
  let component: DecryptPromptComponent;
  let fixture: ComponentFixture<DecryptPromptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DecryptPromptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecryptPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

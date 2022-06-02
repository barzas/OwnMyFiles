import { Component, OnInit } from '@angular/core';
import {ActionService} from '../_services/action.service';
import {TokenStorageService} from '../_services/token-storage.service';

@Component({
  selector: 'app-encypt-prompt',
  templateUrl: './encypt-prompt.component.html',
  styleUrls: ['./encypt-prompt.component.css']
})
export class EncyptPromptComponent implements OnInit {
  form: any = {};
  errorMessage = '';
  isEncrypted = false;
  options = ['ShiftUp', 'Xor', 'ShiftMultiply', 'Double', 'Repeat'];
  ngSelect = 'ShiftUp';
  constructor(private actionService: ActionService) {
  }

  ngOnInit() {
  }
  onSubmit() {
    this.actionService.encrypt(this.form).subscribe(
      data => {
        //TODO
        this.isEncrypted = true;
        this.success();
      },
      error => {
        this.errorMessage = error.error.message;
      }
    );
  }

  success() {
    setTimeout(function() {
      window.location.reload();
      this.isEncrypted = false;
    }.bind(this), 2000);
  }

}

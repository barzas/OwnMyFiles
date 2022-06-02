import { Component, OnInit } from '@angular/core';
import {ActionService} from '../_services/action.service';

@Component({
  selector: 'app-encypt-prompt',
  templateUrl: './encypt-prompt.component.html',
  styleUrls: ['./encypt-prompt.component.css']
})
export class EncyptPromptComponent implements OnInit {
  form: any = {};
  errorMessage = '';
  isEncrypted = false;
  isEncryptFailed = false;
  options = ['ShiftUp', 'Xor', 'ShiftMultiply', 'Double', 'Repeat'];
  ngSelect = 'ShiftUp';
  constructor(private actionService: ActionService) {
  }

  ngOnInit() {
  }
  onSubmit() {
    this.actionService.encrypt(this.form).subscribe(
      data => {
        this.isEncrypted = true;
        this.isEncryptFailed = false;
        this.success();
      },
      error => {
        this.errorMessage = error.error.message;
        console.log(error.message);
        this.isEncryptFailed = true;
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

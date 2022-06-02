import { Component, OnInit } from '@angular/core';
import {ActionService} from '../_services/action.service';

@Component({
  selector: 'app-decrypt-prompt',
  templateUrl: './decrypt-prompt.component.html',
  styleUrls: ['./decrypt-prompt.component.css']
})
export class DecryptPromptComponent implements OnInit {
  form: any = {};
  errorMessage = '';
  isDecrypted = false;
  isDecryptedFailed = false;

  constructor(private actionService: ActionService) {
  }

  ngOnInit() {
  }
  onSubmit() {
    this.actionService.decrypt(this.form).subscribe(
      data => {
        this.isDecrypted = true;
        this.isDecryptedFailed = false;
        this.success();
      },
      error => {
        this.errorMessage = error.error.message;
        console.log(error.message);
        this.isDecryptedFailed = true;
      }
    );
  }

  success() {
    setTimeout(function() {
      window.location.reload();
      this.isDecrypted = false;
    }.bind(this), 2000);
  }

}

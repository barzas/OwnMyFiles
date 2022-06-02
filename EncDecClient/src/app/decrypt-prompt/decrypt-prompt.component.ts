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

  constructor(private actionService: ActionService) {
  }

  ngOnInit() {
  }
  onSubmit() {
    this.actionService.decrypt(this.form).subscribe(
      data => {
        //TODO
        this.isDecrypted = true;
        this.succes();
      },
      error => {
        this.errorMessage = error.error.message;
      }
    );
  }

  succes() {
    setTimeout(function() {
      window.location.reload();
      this.isDecrypted = false;
    }.bind(this), 2000);
  }

}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-encypt-prompt',
  templateUrl: './encypt-prompt.component.html',
  styleUrls: ['./encypt-prompt.component.css']
})
export class EncyptPromptComponent implements OnInit {
  chiphers = [
    {id: 1, name: 'shift'},
    {id: 2, name: 'shift2'},
    {id: 3, name: 'shif3t'}
  ];
  constructor() { }

  ngOnInit() {
  }

}

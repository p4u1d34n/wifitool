import { registerPlugin } from '@capacitor/core';

import type { wifitoolPlugin } from './definitions';

const wifitool = registerPlugin<wifitoolPlugin>('wifitool', {
  web: () => import('./web').then(m => new m.wifitoolWeb()),
});

export * from './definitions';
export { wifitool };

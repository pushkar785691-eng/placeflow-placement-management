const BASE='/api';
export async function api<T>(path:string,options:RequestInit={}):Promise<T>{
 const token=localStorage.getItem('token');
 const response=await fetch(BASE+path,{...options,headers:{'Content-Type':'application/json',...(token?{Authorization:`Bearer ${token}`} :{}),...options.headers}});
 if(!response.ok){let message='Request failed';try{const body=await response.json();message=body.message||message;}catch{}throw new Error(message)}
 return response.status===204?undefined as T:response.json();
}

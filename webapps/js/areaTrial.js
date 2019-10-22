function areacoordsMain(imode){this.version='0.882';mode=typeof imode!=='undefined'?imode:5;console.log("mode",mode);this.modePts=5;if(isNaN(mode)){}else{this.modePts=Number(mode);}
canvasid="canvas"+ mode;titleid="title"+ mode;infoid="info"+ mode;dragQ=false;dragScreenQ=false;w=540;h=360;graphWd=w;graphHt=h;s='';s+='<div style="position:relative; width:'+ w+'px; height:'+ h+'px; margin:auto; display:block; border: 2px solid lightblue; box-shadow: 0px 0px 9px 5px rgba(0,0,68,0.46);">';s+='<canvas id="'+ canvasid+'" width="'+ w+'" height="'+ h+'" style="z-index:10;"></canvas>';s+='<div style="position:absolute; left:95px; top:3px; ">';s+='<div id="'+ titleid+'" style="font: bold 26px arial; position:absolute; left:0px; top:0px; width:540px; text-align:left; z-index:-1;">Title</div>';s+='<div id="'+ infoid+'" style="font: bold 15px arial; color: orange; position:absolute; left:0px; top:30px; width:540px; text-align:left; z-index:-1;">Info</div>';s+='</div>';if(isNaN(mode)){s+='<div id="btns0" style="position:absolute; left:15px; top:3px;">';s+='<button id="upBtn" style="color: #000aae; font-size: 20px;" class="togglebtn"  onclick="numUp()" >&#x25B2;</button>';s+='<br>';s+='<button id="dnBtn" style="color: #000aae; font-size: 20px;" class="togglebtn"  onclick="numDn()" >&#x25BC;</button>';s+='</div>';}
s+='<div id="btns1" style="position:absolute; right:3px; top:3px;">';s+='<button id="anglesBtn" onclick="toggleAngles()" style="z-index:2;" class="togglebtn lo" >Angles</button>';s+='<br>';s+='<button id="sidesBtn" onclick="toggleSides()" style="z-index:2;" class="togglebtn lo" >Sides</button>';s+='<br>';s+='<button id="diagsBtn" onclick="toggleDiags()" style="z-index:2;" class="togglebtn lo" >Diags</button>';s+='<br>';s+='<button id="coordsBtn" onclick="toggleCoords()" style="z-index:2;" class="togglebtn lo" >Coords</button>';s+='<br>';s+='<button id="guidesBtn" onclick="toggleGuides()" style="z-index:2;" class="togglebtn lo" >Guides</button>';s+='</div>';s+='<div id="btns2" style="position:absolute; right:3px; bottom:20px; ">';s+='<span style="color:black; font: 13px/20px Arial; vertical-align: top; z-index: 2; ">Zoom:</span>';s+='<input type="range" id="r1"  value="0.5" min="0" max="1" step=".01"  style="z-index:2; width:200px; height:17px; border: none; " oninput="onZoomChg(0,this.value)" onchange="onZoomChg(1,this.value)" />';s+='<button id="resetBtn" onclick="reset()" style="z-index:2;" class="clickbtn" >Reset</button>';s+='<button id="editBtn" onclick="editpop()" style="z-index:2;" class="clickbtn" >Edit</button>';s+='</div>';s+='<div id="editpop" style="position:absolute; left:-450px; top:40px; padding: 5px; border: 1px solid red; border-radius: 9px; background-color: #88aaff; box-shadow: 10px 10px 5px 0px rgba(40,40,40,0.75); z-index:1; transition: all linear 0.3s; opacity:0; ">';s+='<textarea  id="editbox" value="ddd" style="width: 400px; height: 120px; font: 16px Arial; border: 1px solid red; border-radius: 9px; background-color: #eeeeff; display: block;">';s+='</textarea >';s+='<div style="float:right; margin: 0 0 5px 10px;">';s+='<button onclick="editYes()" style="z-index:2; font: 22px Arial;" class="togglebtn" >&#x2714;</button>';s+='<button onclick="editNo()" style="z-index:2; font: 22px Arial;" class="togglebtn" >&#x2718;</button>';s+='</div>';s+='</div>';s+='</div>';document.write(s);el=document.getElementById(canvasid);ratio=2;el.width=w*ratio;el.height=h*ratio;el.style.width=w+"px";el.style.height=h+"px";g=el.getContext("2d");g.setTransform(ratio,0,0,ratio,0,0);currZoom=1;this.sttRect=[-1,-2.2,11,7.5];this.coords=new Coords(0,0,w,h,sttRect[0],sttRect[1],sttRect[2],sttRect[3],true);this.graph=new Graph(g,coords);theCanvas=el;context=g;dragNo=0;anglesQ=false;coordsQ=false;diagsQ=false;sidesQ=false;guidesQ=false;titleStr="Quadrilateral";descrStr="";shapes=[];pts=[];poly=new Poly();if(mode=="coords"){this.modePts=3;}
makeShapes();if(mode=="coords"){document.getElementById('anglesBtn').style.visibility='hidden';document.getElementById('diagsBtn').style.visibility='hidden';toggleCoords();}else{}
if(mode=="diag"){toggleDiags();}
theCanvas.addEventListener("mousedown",mouseDownListener,false);theCanvas.addEventListener('touchstart',ontouchstart,false);theCanvas.addEventListener("mousemove",dopointer,false);doType();}
function getDescr(){var polyNames=["","","","Triangle","Quadrilateral","Pentagon","Hexagon","Heptagon","Octagon","Nonagon","Decagon","Hendecagon","Dodecagon","Triskaidecagon ","Tetrakaidecagon ","Pentadecagon","Hexakaidecagon ","Heptadecagon","Octakaidecagon ","Enneadecagon","Icosagon"];var area=poly.getArea();area=area*coords.getXScale()*coords.getYScale();var descrStr="";var areaStr="";if(poly.isComplex()){descrStr+="Complex ";areaStr="(Shape intersects itself.)";}else{if(poly.isRegular(0.01)){if(shapes.length>4){descrStr+="Regular ";}}
areaStr="Area = "+ fmt(area,4);}
if(shapes.length<polyNames.length){descrStr+=polyNames[shapes.length];}else{descrStr+=shapes.length.toString()+"-gon";}
if(mode=="diag"){areaStr=poly.getDiagCount()+" Diagonals";}
return[descrStr,areaStr];}
function zoomReset(){coords=new Coords(graphLt,graphTp,graphWd,graphHt,-5,-3,5,3,true);doGraph();}
function editpop(){console.log("editpop");var pop=document.getElementById('editpop');pop.style.transitionDuration="0.3s";pop.style.opacity=1;pop.style.zIndex=12;pop.style.left='100px';document.getElementById('editbox').value=getPts();}
function editYes(){var pop=document.getElementById('editpop');pop.style.opacity=0;pop.style.zIndex=1;pop.style.left='-500px';var s=document.getElementById('editbox').value;console.log("editYes",s);decodeCSV(s);}
function editNo(){var pop=document.getElementById('editpop');pop.style.opacity=0;pop.style.zIndex=1;pop.style.left='-500px';}
function getPts(){var s='';for(var i=0;i<shapes.length;i++){if(i>0)s+=', ';s+='(';s+=shapes[i].x.toFixed(2);s+=',';s+=shapes[i].y.toFixed(2);s+=')';}
return s;}
function decodeCSV(s){s=s.replace(/\s/g,"");s=s.replace(/\)\,\(/g,"~");s=s.replace(/\)\(/g,"~");s=s.replace(/\)/g,"");s=s.replace(/\(/g,"");s=s.replace(/\,/g,"_");decode(s);}
function decode(s){var a=s.split("~");shapes=[];for(var i=0;i<a.length;i++){var s1=a[i];var xy=s1.split("_");if(xy.length>=2){shapes.push(new Pt(Number(xy[0]),Number(xy[1])));}}
console.log("shapes",shapes);doType();}
function reset(){this.coords=new Coords(0,0,w,h,sttRect[0],sttRect[1],sttRect[2],sttRect[3],true);makeShapes();update();}
function update(){doType();}
function getNum(){return parseInt(this.modePts);}
function numDn(){var num=getNum();if(num>3){num--;chgNumPts(num);}}
function numUp(){var num=getNum();if(num<=100){num++;chgNumPts(num);}}
function chgNumPts(n){this.modePts=n;makeShapes();doType();}
function toggleAngles(){anglesQ=!anglesQ;toggleBtn("anglesBtn",anglesQ);update();}
function toggleCoords(){coordsQ=!coordsQ;toggleBtn("coordsBtn",coordsQ);update();}
function toggleGuides(){guidesQ=!guidesQ;toggleBtn("guidesBtn",guidesQ);update();}
function toggleDiags(){diagsQ=!diagsQ;toggleBtn("diagsBtn",diagsQ);update();}
function toggleSides(){sidesQ=!sidesQ;toggleBtn("sidesBtn",sidesQ);update();}
function toggleBtn(btn,onq){if(onq){document.getElementById(btn).classList.add("hi");document.getElementById(btn).classList.remove("lo");}else{document.getElementById(btn).classList.add("lo");document.getElementById(btn).classList.remove("hi");}}
function drawBG(w,h){g.lineWidth=1;for(i=0;i<10;i++){var xPix=i*60;g.beginPath();if(i%2){g.strokeStyle="rgba(0,0,256,0.2)";}else{g.strokeStyle="rgba(0,0,256,0.1)";}
g.moveTo(xPix,0);g.lineTo(xPix,h);g.stroke();}
for(i=0;i<6;i++){var yPix=i*60;g.beginPath();if(i%2){g.strokeStyle="rgba(0,0,256,0.2)";}else{g.strokeStyle="rgba(0,0,256,0.1)";}
g.moveTo(0,yPix);g.lineTo(w,yPix);g.stroke();}}
function ontouchstart(evt){var touch=evt.targetTouches[0];evt.clientX=touch.clientX;evt.clientY=touch.clientY;evt.touchQ=true;mouseDownListener(evt)}
function ontouchmove(evt){var touch=evt.targetTouches[0];evt.clientX=touch.clientX;evt.clientY=touch.clientY;evt.touchQ=true;mouseMoveListener(evt);evt.preventDefault();};function ontouchend(evt){theCanvas.addEventListener('touchstart',ontouchstart,false);window.removeEventListener("touchend",ontouchend,false);if(dragQ){dragQ=false;window.removeEventListener("touchmove",ontouchmove,false);}}
function dopointer(e){var bRect=theCanvas.getBoundingClientRect();mouseX=(e.clientX- bRect.left)*(el.width/ratio/bRect.width);mouseY=(e.clientY- bRect.top)*(el.height/ratio/bRect.height);var overQ=false;for(i=0;i<numPts;i++){if(hitTest(pts[i],mouseX,mouseY)){overQ=true;dragNo=i;}}
if(overQ){document.body.style.cursor="pointer";}else{document.body.style.cursor="default";}}
function domousemove(e){document.body.style.cursor="default";var bRect=theCanvas.getBoundingClientRect();mouseX=(e.clientX- bRect.left)*(el.width/ratio/bRect.width);mouseY=(e.clientY- bRect.top)*(el.height/ratio/bRect.height);for(i=0;i<numPts;i++){if(hitTest(pts[i],mouseX,mouseY)){dragNo=i;document.body.style.cursor="pointer";}}}
function mouseDownListener(evt){var i;var highestIndex=-1;var bRect=theCanvas.getBoundingClientRect();mouseX=(evt.clientX- bRect.left)*(el.width/ratio/bRect.width);mouseY=(evt.clientY- bRect.top)*(el.height/ratio/bRect.height);downX=mouseX;downY=mouseY;prevX=mouseX;prevY=mouseY;dragQ=false;dragScreenQ=false;for(i=0;i<numPts;i++){if(hitTest(pts[i],mouseX,mouseY)){dragNo=i;dragQ=true;if(i>highestIndex){dragHoldX=mouseX- pts[i].x;dragHoldY=mouseY- pts[i].y;highestIndex=i;dragNo=i;}}}
if(!dragQ){dragQ=true;dragScreenQ=true;}
if(evt.touchQ){window.addEventListener('touchmove',ontouchmove,false);}else{window.addEventListener("mousemove",mouseMoveListener,false);}
console.log("mouseDownListener",dragQ,dragScreenQ,highestIndex);doType();if(evt.touchQ){theCanvas.removeEventListener("touchstart",ontouchstart,false);window.addEventListener("touchend",ontouchend,false);}else{theCanvas.removeEventListener("mousedown",mouseDownListener,false);window.addEventListener("mouseup",mouseUpListener,false);}
if(evt.preventDefault){evt.preventDefault();}
else if(evt.returnValue){evt.returnValue=false;}
return false;}
function mouseUpListener(evt){dragQ=false;theCanvas.addEventListener("mousedown",mouseDownListener,false);window.removeEventListener("mouseup",mouseUpListener,false);window.removeEventListener("mousemove",mouseMoveListener,false);}
function mouseMoveListener(evt){var posX;var posY;var shapeRad=shapes[dragNo].rad;var minX=shapeRad;var maxX=theCanvas.width- shapeRad;var minY=shapeRad;var maxY=theCanvas.height- shapeRad;var bRect=theCanvas.getBoundingClientRect();mouseX=(evt.clientX- bRect.left)*(el.width/ratio/bRect.width);mouseY=(evt.clientY- bRect.top)*(el.height/ratio/bRect.height);if(dragScreenQ){var moveX=prevX- mouseX;var moveY=mouseY- prevY;if(Math.abs(moveX)>1||Math.abs(moveY)>1){coords.drag(moveX,moveY);prevX=mouseX;prevY=mouseY;}}else{posX=mouseX- dragHoldX;posX=(posX<minX)?minX:((posX>maxX)?maxX:posX);posY=mouseY- dragHoldY;posY=(posY<minY)?minY:((posY>maxY)?maxY:posY);shapes[dragNo].x=coords.toXVal(posX);shapes[dragNo].y=coords.toYVal(posY);}
doType();}
function hitTest(shape,mx,my){var dx;var dy;dx=mx- shape.x;dy=my- shape.y;return(dx*dx+ dy*dy<shape.rad*shape.rad);}
function onZoomChg(n,v){var scaleBy=(v*2+ 0.01)/ currZoom; // add 0.01 to avoid zero
coords.scale(scaleBy);currZoom*=scaleBy;if(n==1){currZoom=1;document.getElementById('r1').value=0.5;}
doType();}
function doType(){g.clearRect(0,0,el.width,el.height);this.graph.drawGraph();drawPts();var descr=['',''];if(mode=='coords'){}else{descr=getDescr();}
document.getElementById(titleid).innerHTML=descr[0];document.getElementById(infoid).innerHTML=descr[1];}
function getRegular(midX,midY,radius,sttAngle,n){var pts=[];var dAngle=Math.PI*2/n;for(var i=0;i<n;i++){var angle=sttAngle+ i*dAngle;var x=midX+ radius*Math.cos(angle);var y=midY+ radius*Math.sin(angle);pts.push(new Pt(x,y));}
return pts;}
function makeShapes(){var i;var tempX;var tempY;var tempColor;var pos=getRegular(4,3,3,1.2,this.modePts);shapes=[];console.log("makeShapes",this.modePts);for(i=0;i<this.modePts;i++){shapes.push(pos[i]);}
poly.pts=pts;}
function drawPts(){var i;g.strokeStyle="rgba(0, 0, 255, 0.5)";g.fillStyle="rgba(255, 255, 100, 0.1)";numPts=shapes.length;pts=[];for(i=0;i<numPts;i++){var pt=new Pt(coords.toXPix(shapes[i].x),coords.toYPix(shapes[i].y));pts.push(pt);}
if(guidesQ){var orig={x:coords.toXPix(0),y:coords.toYPix(0)};g.lineWidth=1.5;for(i=0;i<numPts;i++){var pt=pts[i];g.beginPath();g.strokeStyle="rgba(0, 0, 0, 0.5)";g.moveTo(orig.x,pt.y);g.lineTo(pt.x,pt.y);g.stroke();g.beginPath();g.moveTo(pt.x,pt.y);g.lineTo(pt.x,orig.y);g.stroke();}}
drawShapeQ=true;if(mode=='coords'){drawShapeQ=sidesQ;}
if(drawShapeQ){g.lineWidth=2;g.beginPath();g.moveTo(pts[numPts- 1].x,pts[numPts- 1].y)
for(i=0;i<numPts;i++){g.lineTo(pts[i].x,pts[i].y);}
g.fill();g.stroke();}
g.lineWidth=2;var dbg="";for(i=0;i<numPts;i++){var pt=pts[i];g.fillStyle="rgba(0, 0, 255, 0.3)";g.beginPath();g.arc(pt.x,pt.y,pt.rad,0,2*Math.PI,false);g.closePath();g.fill();g.fillStyle="rgba(0, 0, 0, 0.8)";g.beginPath();g.arc(pt.x,pt.y,2,0,2*Math.PI,false);g.closePath();g.fill();g.textAlign="left";if(coordsQ){g.font="bold 14px Arial";var txt='(';txt+=shapes[i].x.toFixed(1);txt+=',';txt+=shapes[i].y.toFixed(1);txt+=')';g.fillText(txt,pt.x+ 5,pt.y- 9);}else{g.font="14px Arial";g.fillText(String.fromCharCode(65+ i),shapes[i].x+ 5,shapes[i].y- 9);}
dbg+='['+ Math.floor(shapes[i].x)+","+ Math.floor(shapes[i].y)+"],";}
poly.pts=pts;poly.updateMe();if(anglesQ)poly.drawAngles(g);if(diagsQ)poly.drawDiags(g);if(sidesQ)poly.drawSides(g);}
function Poly(){this.pts=[];}
Poly.prototype.updateMe=function(){setAngles(this.pts);sides=getSides(this.pts);}
Poly.prototype.drawDiags=function(g){g.strokeStyle="#666666";var diagCount=0;for(var i=0;i<this.pts.length- 2;i++){for(var j=i+ 2;j<this.pts.length;j++){if(i==0&&j==this.pts.length- 1){}else{g.beginPath();g.moveTo(this.pts[i].x,this.pts[i].y);g.lineTo(this.pts[j].x,this.pts[j].y);g.stroke();diagCount++;}}}
console.log("diagCount="+ diagCount);}
Poly.prototype.drawSides=function(g){var ptC=new Pt();ptC.setAvg(this.pts);g.fillStyle="#000000";g.font="bold 12px Arial";var ptM=new Pt();var ptsLen=this.pts.length;for(var i=0;i<ptsLen;i++){var pt=this.pts[i];ptM.setAvg([this.pts[i],this.pts[loop(i,0,ptsLen- 1,1)]]);ptM.interpolate(ptM,ptC,1.2);var side=sides[loop(i- 1,0,ptsLen- 1,1)];side=(coords.xScale*side).toFixed(2);g.fillText(side,ptM.x- 10,ptM.y+ 5,100);}}
Poly.prototype.drawAngles=function(g){var angSum=0;var angDescr="";var angs=[];for(var i=0;i<this.pts.length;i++){var pt=this.pts[i];var angDeg=Math.round(pt.getAngle()*180/Math.PI);var d=30;if(angDeg==90){g.strokeStyle='#888888';g.lineWidth=2;g.drawBox(pt.x,pt.y,25,pt.angleOut- Math.PI/2);}else{if(angDeg>90){g.strokeStyle='#ff0000';g.lineWidth=2;d=Math.max(20,30-(angDeg- 90)/ 6); // slightly smaller diameter looks better on large angles
}else{g.strokeStyle='#4444FF';g.lineWidth=2;}
g.fillStyle="rgba(0, 0, 255, 0.3)";g.beginPath();g.moveTo(pt.x,pt.y);g.arc(pt.x,pt.y,d,pt.angleIn,pt.angleOut,false);g.closePath();g.fill();}
var ang=this.userAngle(pt.getAngle());if(i<this.pts.length- 1){angSum+=ang;}else{ang=(this.pts.length- 2)*180- angSum;if(ang<0)ang+=360;}
angs[i]=ang;angDescr+=ang+"° + "
var aMid=(pt.angleIn+ pt.angleOut)/ 2;
var txtPt=new Pt(0,0)
txtPt.x=pt.x+(d+ 15)*Math.cos(aMid)- 0
txtPt.y=pt.y+(d+ 15)*Math.sin(aMid)- 0
g.fillStyle="rgba(0, 0, 255, 1)";g.fillText(Math.round(ang)+"°",txtPt.x- 10,txtPt.y+ 5,100);}
return angs}
Poly.prototype.userAngle=function(x){return Math.round(x*180/Math.PI,this.dec);}
Poly.prototype.getArea=function(){var a=0;for(var i=0;i<this.pts.length;i++){var pt0=this.pts[i];var pt1=this.pts[loop(i,0,this.pts.length- 1,1)];var a1=(pt0.x*pt1.y- pt0.y*pt1.x);a+=a1;}
a=Math.abs(a)/ 2; // note: clockwise gives positive area
return a;}
Poly.prototype.getDiagCount=function(){var n=this.pts.length;return n*(n- 3)/ 2;
}
Poly.prototype.isComplex=function(){var lns=[]
for(var i=0;i<this.pts.length;i++){lns.push(new Line(this.pts[i],this.pts[loop(i,0,this.pts.length- 1,1)]));}
for(i=0;i<this.pts.length- 1;i++){for(var j=i+ 2;j<this.pts.length;j++){if(i==0&&j==this.pts.length- 1)
continue;var ln=lns[i];if(ln.isIntersect(lns[j])){if(ln.getIntersection(lns[j],true)==null){}else{return true;}}}}
return false;}
Poly.prototype.isRegular=function(tolerRatio){tolerRatio=typeof tolerRatio!=='undefined'?tolerRatio:0.001;var ptC=new Pt;ptC.setAvg(this.pts);var rads=[];var lens=[];for(var i=0;i<this.pts.length;i++){var pt=this.pts[i];rads[i]=dist(pt.x- ptC.x,pt.y- ptC.y);var nxt=(i+ 1)%this.pts.length;var ptN=this.pts[nxt];lens[i]=dist(pt.x- ptN.x,pt.y- ptN.y);}
var radAvg=avg(rads);var lenAvg=avg(lens);var toler=radAvg*tolerRatio;var sameQ=true;for(i=0;i<this.pts.length;i++){if(!isNear(rads[i],radAvg,toler)){sameQ=false;break;}
if(!isNear(lens[i],lenAvg,toler)){sameQ=false;break;}}
return sameQ;}
function Pt(ix,iy){this.x=ix;this.y=iy;this.rad=9;this.color="rgb("+ 0+","+ 0+","+ 255+")";var prevx;var prevy;var a;var prevQ=false;var validPtQ;angleIn=0;angleOut=0;}
Pt.prototype.setxy=function(ix,iy){this.x=ix;this.y=iy;validPtQ=true;}
Pt.prototype.setPrevPt=function(){prevx=this.x
prevy=this.y;prevQ=true;}
Pt.prototype.getAngle=function(){return this.angleOut- this.angleIn;}
Pt.prototype.drawMe=function(g){g.fillStyle="rgba(0, 0, 255, 0.3)";g.beginPath();g.arc(this.x,this.y,20,0,2*Math.PI,false);g.closePath();g.fill();}
Pt.prototype.getAvg=function(pts){var xSum=0;var ySum=0;for(var i=0;i<pts.length;i++){xSum+=pts[i].x;ySum+=pts[i].y;}
newPt=new Pt(xSum/pts.length,ySum/pts.length);newPt.x=xSum/pts.length;newPt.y=ySum/pts.length;return newPt;}
Pt.prototype.setAvg=function(pts){this.setPrevPt();var newPt=this.getAvg(pts);this.x=newPt.x;this.y=newPt.y;validPtQ=true;}
Pt.prototype.interpolate=function(pt1,pt2,f){this.setPrevPt();this.x=pt1.x*f+ pt2.x*(1- f);this.y=pt1.y*f+ pt2.y*(1- f);validPtQ=true;}
function isNear(a,b,toler){if(Math.abs(a- b)<=toler){return true;}else{return false;}}
Pt.prototype.translate=function(pt,addQ){addQ=typeof addQ!=='undefined'?addQ:true;t=new Pt(this.x,this.y);t.x=this.x
t.y=this.y
if(addQ){t.x+=pt.x;t.y+=pt.y}else{t.x-=pt.x;t.y-=pt.y}
return t;}
Pt.prototype.multiply=function(fact){return new Pt(this.x*fact,this.y*fact);}
Pt.prototype.multiplyMe=function(fact){this.x*=fact;this.y*=fact;}
function setAngles(pts){var CW=getClockwise(pts);var numPoints=pts.length;var angles=[];for(var i=0;i<numPoints;i++){var pt=pts[i];var ptm1=pts[loop(i,0,numPoints- 1,-1)];var ptp1=pts[loop(i,0,numPoints- 1,1)];var a1=Math.atan2(ptm1.y- pt.y,ptm1.x- pt.x);var a2=Math.atan2(ptp1.y- pt.y,ptp1.x- pt.x);if(CW==1){var temp=a1;a1=a2;a2=temp;}
if(a1>a2)
a2+=2*Math.PI;pt.angleIn=a1;pt.angleOut=a2;}}
function getClockwise(pts){var numPoints=pts.length;var count=0;for(var i=0;i<numPoints;i++){var pt=pts[i];var ptm1=pts[loop(i,0,numPoints- 1,-1)];var ptp1=pts[loop(i,0,numPoints- 1,1)];var z=0;z+=(pt.x- ptm1.x)*(ptp1.y- pt.y);z-=(pt.y- ptm1.y)*(ptp1.x- pt.x);if(z<0){count--;}else if(z>0){count++;}}
if(count>0)
return(1);if(count==0)
return(0);return(-1);}
function getSides(pts){var numPoints=pts.length;var sides=[];for(var i=0;i<numPoints;i++){var pt=pts[i];var ptp1=pts[loop(i,0,numPoints- 1,1)];sides.push(dist(ptp1.x- pt.x,ptp1.y- pt.y));}
return(sides);}
function avg(vals){var sum=0;var count=vals.length;for(var i=0;i<count;i++){sum+=vals[i];}
return(sum/count);}
function dist(dx,dy){return(Math.sqrt(dx*dx+ dy*dy));}
function loop(currNo,minNo,maxNo,incr){currNo+=incr;var was=currNo;var range=maxNo- minNo+ 1;if(currNo<minNo){currNo=maxNo-(-currNo+ maxNo)%range;}
if(currNo>maxNo){currNo=minNo+(currNo- minNo)%range;}
return currNo;}
function constrain(min,val,max){return(Math.min(Math.max(min,val),max));}
function Line(pt1,pt2){this.a=pt1;this.b=pt2;}
Line.prototype.isIntersect=function(b){var a=this;if(this.ccw(a.a,a.b,b.a)*this.ccw(a.a,a.b,b.b)>0){return false;}
if(this.ccw(b.a,b.b,a.a)*this.ccw(b.a,b.b,a.b)>0){return false;}
return true;}
Line.prototype.ccw=function(a,b,c){return((b.x- a.x)*(c.y- a.y)-(c.x- a.x)*(b.y- a.y));}
Line.prototype.getIntersection=function(ln,asSegmentsQ){var A=this.a;var B=this.b;var E=ln.a;var F=ln.b;var a1=B.y- A.y;var b1=A.x- B.x;var c1=B.x*A.y- A.x*B.y;var a2=F.y- E.y;var b2=E.x- F.x;var c2=F.x*E.y- E.x*F.y;var denom=a1*b2- a2*b1;if(denom==0){return null;}
var ip=new Pt();ip.x=(b1*c2- b2*c1)/ denom;
ip.y=(a2*c1- a1*c2)/ denom;
if(asSegmentsQ){if(Math.pow(ip.x- B.x,2)+ Math.pow(ip.y- B.y,2)>Math.pow(A.x- B.x,2)+ Math.pow(A.y- B.y,2)){return null;}
if(Math.pow(ip.x- A.x,2)+ Math.pow(ip.y- A.y,2)>Math.pow(A.x- B.x,2)+ Math.pow(A.y- B.y,2)){return null;}
if(Math.pow(ip.x- F.x,2)+ Math.pow(ip.y- F.y,2)>Math.pow(E.x- F.x,2)+ Math.pow(E.y- F.y,2)){return null;}
if(Math.pow(ip.x- E.x,2)+ Math.pow(ip.y- E.y,2)>Math.pow(E.x- F.x,2)+ Math.pow(E.y- F.y,2)){return null;}}
return ip;}
Line.prototype.getMidPt=function(){return new Pt((this.a.x+ this.b.x)/ 2, (this.a.y + this.b.y) / 2);
}
Line.prototype.getClosestPoint=function(toPt,inSegmentQ){var AP=toPt.translate(this.a,false);var AB=this.b.translate(this.a,false);var ab2=AB.x*AB.x+ AB.y*AB.y;var ap_ab=AP.x*AB.x+ AP.y*AB.y;var t=ap_ab/ab2;if(inSegmentQ){t=constrain(0,t,1);}
closest=this.a.translate(AB.multiply(t));return closest;}
Line.prototype.setLen=function(newLen,fromMidQ){fromMidQ=typeof fromMidQ!=='undefined'?fromMidQ:true;var len=this.getLength();if(fromMidQ){var midPt=this.getMidPt();var halfPt=new Pt(this.a.x- midPt.x,this.a.y- midPt.y);halfPt.multiplyMe(newLen/len);pt1=midPt.translate(halfPt);pt2=midPt.translate(halfPt,false);}else{var diffPt=new Pt(this.a.x- this.b.x,this.a.y- this.b.y);diffPt.multiplyMe(newLen/len);pt2=pt1.translate(diffPt,false);}}
Line.prototype.getAngle=function(){return Math.atan2(this.b.y- this.a.y,this.b.x- this.a.x);}
function Coords(left,top,width,height,xStt,yStt,xEnd,yEnd,uniScaleQ){this.left=left;this.top=top;this.width=width;this.height=height;this.xStt=xStt;this.yStt=yStt;this.xEnd=xEnd;this.yEnd=yEnd;this.uniScaleQ=uniScaleQ;this.xLogQ=false;this.yLogQ=false;this.xScale;var xLogScale;this.yScale;this.calcScale();}
Coords.prototype.calcScale=function(){if(this.xLogQ){if(this.xStt<=0)
this.xStt=1;if(this.xEnd<=0)
this.xEnd=1;}
if(this.yLogQ){if(this.yStt<=0)
this.yStt=1;if(this.yEnd<=0)
this.yEnd=1;}
var temp;if(this.xStt>this.xEnd){temp=this.xStt;this.xStt=this.xEnd;this.xEnd=temp;}
if(this.yStt>this.yEnd){temp=this.yStt;this.yStt=this.yEnd;this.yEnd=temp;}
var xSpan=this.xEnd- this.xStt;if(xSpan<=0)
xSpan=0.1;this.xScale=xSpan/this.width;this.xLogScale=(Math.log(this.xEnd)- Math.log(this.xStt))/ this.width;
var ySpan=this.yEnd- this.yStt;if(ySpan<=0)
ySpan=0.1;this.yScale=ySpan/this.height;this.yLogScale=(Math.log(this.yEnd)- Math.log(this.yStt))/ this.height;
if(this.uniScaleQ&&!this.xLogQ&&!this.yLogQ){var newScale=Math.max(this.xScale,this.yScale);this.xScale=newScale;xSpan=this.xScale*this.width;var xMid=(this.xStt+ this.xEnd)/ 2;
this.xStt=xMid- xSpan/2;this.xEnd=xMid+ xSpan/2;this.yScale=newScale;ySpan=this.yScale*this.height;var yMid=(this.yStt+ this.yEnd)/ 2;
this.yStt=yMid- ySpan/2;this.yEnd=yMid+ ySpan/2;}}
Coords.prototype.getXScale=function(){return this.xScale;}
Coords.prototype.getYScale=function(){return this.yScale;}
Coords.prototype.scale=function(factor,xMid,yMid){if(typeof xMid=='undefined')xMid=(this.xStt+ this.xEnd)/ 2;
this.xStt=xMid-(xMid- this.xStt)*factor;this.xEnd=xMid+(this.xEnd- xMid)*factor;if(typeof yMid=='undefined')yMid=(this.yStt+ this.yEnd)/ 2;
this.yStt=yMid-(yMid- this.yStt)*factor;this.yEnd=yMid+(this.yEnd- yMid)*factor;this.calcScale();};Coords.prototype.drag=function(xPix,yPix){this.xStt+=xPix*this.xScale;this.xEnd+=xPix*this.xScale;this.yStt+=yPix*this.yScale;this.yEnd+=yPix*this.yScale;this.calcScale();};Coords.prototype.newCenter=function(x,y){var xMid=this.xStt+ x*this.xScale;var xhalfspan=(this.xEnd- this.xStt)/ 2;
this.xStt=xMid- xhalfspan;this.xEnd=xMid+ xhalfspan;var yMid=this.yEnd- y*this.yScale;var yhalfspan=(this.yEnd- this.yStt)/ 2;
this.yStt=yMid- yhalfspan;this.yEnd=yMid+ yhalfspan;this.calcScale();};Coords.prototype.fitToPts=function(pts,borderFactor){for(var i=0;i<pts.length;i++){var pt=pts[i];if(i==0){this.xStt=pt.x;this.xEnd=pt.x;this.yStt=pt.y;this.yEnd=pt.y;}else{this.xStt=Math.min(this.xStt,pt.x);this.xEnd=Math.max(this.xEnd,pt.x);this.yStt=Math.min(this.yStt,pt.y);this.yEnd=Math.max(this.yEnd,pt.y);}}
var xMid=(this.xStt+ this.xEnd)/ 2;
var xhalfspan=borderFactor*(this.xEnd- this.xStt)/ 2;
this.xStt=xMid- xhalfspan;this.xEnd=xMid+ xhalfspan;var yMid=(this.yStt+ this.yEnd)/ 2;
var yhalfspan=borderFactor*(this.yEnd- this.yStt)/ 2;
this.yStt=yMid- yhalfspan;this.yEnd=yMid+ yhalfspan;this.calcScale();};Coords.prototype.toXPix=function(val,useCornerQ){if(this.xLogQ){return this.left+(Math.log(val)- Math.log(xStt))/ this.xLogScale;
}else{return this.left+((val- this.xStt)/ this.xScale);
}};Coords.prototype.toYPix=function(val){if(this.yLogQ){return this.top+(Math.log(yEnd)- Math.log(val))/ this.yLogScale;
}else{return this.top+((this.yEnd- val)/ this.yScale);
}};Coords.prototype.toPtVal=function(pt,useCornerQ){return new Pt(this.toXVal(pt.x,useCornerQ),this.toYVal(pt.y,useCornerQ));};Coords.prototype.toXVal=function(pix,useCornerQ){if(useCornerQ){return this.xStt+(pix- this.left)*this.xScale;}else{return this.xStt+ pix*this.xScale;}};Coords.prototype.toYVal=function(pix,useCornerQ){if(useCornerQ){return this.yEnd-(pix- this.top)*this.yScale;}else{return this.yEnd- pix*this.yScale;}};Coords.prototype.getTicks=function(stt,span,ratio){var ticks=[];var inter=this.tickInterval(span/ratio,false);var tickStt=Math.ceil(stt/inter)*inter;var i=0;do{var tick=tickStt+ i*inter;tick=Number(tick.toPrecision(8));ticks.push([tick,1]);i++;}while(tick<stt+ span);inter=this.tickInterval(span/ratio,true);for(i=0;i<ticks.length;i++){var t=ticks[i][0];if(Math.abs(Math.round(t/inter)-(t/inter))<0.001){ticks[i][1]=0;}}
return ticks;};Coords.prototype.tickInterval=function(span,majorQ){var pow10=Math.pow(10,Math.floor(Math.log(span)*Math.LOG10E));var mantissa=span/pow10;if(mantissa>=5){if(majorQ){return(5*pow10);}else{return(1*pow10);}}
if(mantissa>=3){if(majorQ){return(2*pow10);}else{return(0.2*pow10);}}
if(mantissa>=1.4){if(majorQ){return(0.5*pow10);}else{return(0.2*pow10);}}
if(mantissa>=0.8){if(majorQ){return(0.5*pow10);}else{return(0.1*pow10);}}
if(majorQ){return(0.2*pow10);}else{return(0.1*pow10);}};function Graph(g,coords){this.g=g;this.coords=coords;this.xLinesQ=true;this.yLinesQ=true;this.xValsQ=true;this.yValsQ=true;this.skewQ=false;}
Graph.prototype.drawGraph=function(){this.hzAxisY=coords.toYPix(0);if(this.hzAxisY<0)this.hzAxisY=0;if(this.hzAxisY>coords.height)this.hzAxisY=coords.height;this.hzNumsY=this.hzAxisY+ 14;if(this.hzAxisY>coords.height- 10)this.hzNumsY=coords.height- 3;this.vtAxisX=coords.toXPix(0);if(this.vtAxisX<0)this.vtAxisX=0;if(this.vtAxisX>coords.width)this.vtAxisX=coords.width;this.vtNumsX=this.vtAxisX- 5;if(this.vtAxisX<10)this.vtNumsX=20;if(coords.xLogQ){this.drawLinesLogX();}else{if(this.xLinesQ){this.drawHzLines();}}
if(coords.yLogQ){this.drawLinesLogY();}else{if(this.yLinesQ){this.drawVtLines();}}};Graph.prototype.drawVtLines=function(){var g=this.g;g.lineWidth=1;var ticks=coords.getTicks(coords.xStt,coords.xEnd- coords.xStt,graphWd/100);for(var i=0;i<ticks.length;i++){var tick=ticks[i];var xVal=tick[0];var tickLevel=tick[1];if(tickLevel==0){g.strokeStyle="rgba(0,0,256,0.3)";}else{g.strokeStyle="rgba(0,0,256,0.1)";}
var xPix=coords.toXPix(xVal,false);g.beginPath();g.moveTo(xPix,coords.toYPix(coords.yStt,false));g.lineTo(xPix,coords.toYPix(coords.yEnd,false));g.stroke();if(tickLevel==0&&this.xValsQ){g.fillStyle="#0000ff";g.font="bold 12px Verdana";g.textAlign="center";g.fillText(fmt(xVal),xPix,this.hzNumsY);}}
if(this.skewQ)
return;g.lineWidth=1.5;g.strokeStyle="#ff0000";g.beginPath();g.moveTo(this.vtAxisX,coords.toYPix(coords.yStt,false));g.lineTo(this.vtAxisX,coords.toYPix(coords.yEnd,false));g.stroke();g.beginPath();g.fillStyle=g.strokeStyle;g.drawArrow(this.vtAxisX,coords.toYPix(coords.yEnd),15,2,20,10,Math.PI/2,10,false);g.stroke();g.fill();};Graph.prototype.drawHzLines=function(){var g=this.g;g.lineWidth=1;var ticks=coords.getTicks(coords.yStt,coords.yEnd- coords.yStt,graphHt/100);for(var i=0;i<ticks.length;i++){var tick=ticks[i];var yVal=tick[0];var tickLevel=tick[1];if(tickLevel==0){g.strokeStyle="rgba(0,0,256,0.3)";}else{g.strokeStyle="rgba(0,0,256,0.1)";}
var yPix=coords.toYPix(yVal,false);g.beginPath();g.moveTo(coords.toXPix(coords.xStt,false),yPix);g.lineTo(coords.toXPix(coords.xEnd,false),yPix);g.stroke();if(tickLevel==0&&this.yValsQ){g.fillStyle="#ff0000";g.font="bold 12px Verdana";g.textAlign="right";g.fillText(fmt(yVal),this.vtNumsX,yPix+ 5);}}
if(this.skewQ)
return;g.lineWidth=2;g.strokeStyle="#0000ff";g.beginPath();g.moveTo(coords.toXPix(coords.xStt,false),this.hzAxisY);g.lineTo(coords.toXPix(coords.xEnd,false),this.hzAxisY);g.stroke();g.beginPath();g.fillStyle=g.strokeStyle;g.drawArrow(coords.toXPix(coords.xEnd,false),this.hzAxisY,15,2,20,10,0,10,false);g.stroke();g.fill();};CanvasRenderingContext2D.prototype.drawArrow=function(x0,y0,totLen,shaftHt,headLen,headHt,angle,sweep,invertQ){var g=this;var pts=[[0,0],[-headLen,-headHt/2],[-headLen+ sweep,-shaftHt/2],[-totLen,-shaftHt/2],[-totLen,shaftHt/2],[-headLen+ sweep,shaftHt/2],[-headLen,headHt/2],[0,0]];if(invertQ){pts.push([0,-headHt/2],[-totLen,-headHt/2],[-totLen,headHt/2],[0,headHt/2]);}
for(var i=0;i<pts.length;i++){var cosa=Math.cos(-angle);var sina=Math.sin(-angle);var xPos=pts[i][0]*cosa+ pts[i][1]*sina;var yPos=pts[i][0]*sina- pts[i][1]*cosa;if(i==0){g.moveTo(x0+ xPos,y0+ yPos);}else{g.lineTo(x0+ xPos,y0+ yPos);}}};CanvasRenderingContext2D.prototype.drawBox=function(midX,midY,radius,angle){g.beginPath();var pts=[[0,0],[Math.cos(angle),Math.sin(angle)],[Math.cos(angle)+ Math.cos(angle+ Math.PI/2),Math.sin(angle)+ Math.sin(angle+ Math.PI/2)],[Math.cos(angle+ Math.PI/2),Math.sin(angle+ Math.PI/2)],[0,0]];for(var i=0;i<pts.length;i++){if(i==0){g.moveTo(midX+ radius*pts[i][0],midY+ radius*pts[i][1]);}else{g.lineTo(midX+ radius*pts[i][0],midY+ radius*pts[i][1]);}}
g.stroke();};function fmt(num,digits){digits=14;if(num==Number.POSITIVE_INFINITY)
return"undefined";if(num==Number.NEGATIVE_INFINITY)
return"undefined";num=num.toPrecision(digits);num=num.replace(/0+$/,"");if(num.charAt(num.length- 1)==".")num=num.substr(0,num.length- 1);if(Math.abs(num)<1e-15)num=0;return num;}
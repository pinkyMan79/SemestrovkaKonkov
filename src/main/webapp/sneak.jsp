<%@ page import="com.miusicmaker.musmk.model.User" %><%--
  Created by IntelliJ IDEA.
  User: terenin
  Date: 20.10.2022
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getParameter("level") != null && request.getParameter("ini_len") != null) {
        int $level = 1;
        try {
            $level = Integer.parseInt(request.getParameter("level"));
        } catch (Exception e) {
        }
        int $ini_len = 0;
        try {
            $ini_len = Integer.parseInt(request.getParameter("ini_len"));
        } catch (Exception e) {
        }
        if ($level < 0) {
            $level = 1;
        }
        if ($level > 10) {
            $level = 10;
        }
        if ($ini_len < 1) {
            $ini_len = 1;
        }
        if ($ini_len > 20) {
            $ini_len = 20;
        }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Snake v1.0</title>
    <script type="text/javascript">
        window.onload = function(){

            // Constants
            var KEY_UP = 38;
            var KEY_LEFT = 37;
            var KEY_DOWN = 40;
            var KEY_RIGHT = 39;
            var DIR_UP = 38;
            var DIR_LEFT = 37;
            var DIR_DOWN = 40;
            var DIR_RIGHT = 39;
            var ground_width = window.innerWidth;
            var ground_height = window.innerHeight;
            var snake_width = 15;
            var snake_audio = new Audio("b.ogg");
            var snake_speed = 100;
            var snake_initial_length = <%= $ini_len%>;

            var game_level =  <%= $level%>;
            var speed_increment = 10;
            var game_score = 0;

            var body = document.getElementsByTagName('body')[0];
            body.style.padding = "0px";
            body.style.margin = "0px";


            // Snake ground
            var snake_ground = document.createElement("div");
            snake_ground.style.background = "#000000";
            snake_ground.style.display = "block";
            snake_ground.style.width = ground_width + "px";
            snake_ground.style.height = ground_height + "px";
            snake_ground.style.margin = "0px";
            snake_ground.style.padding = "0px";
            snake_ground.style.position = "relative";
            snake_ground.style.overflow = "hidden";

            // Add snake ground
            body.appendChild(snake_ground);

            // Food block
            var food = document.createElement("div");

            var food_position = [0,0]

            var boundary = 50;
            var max1 = ground_width - boundary;
            var max2 = ground_height - boundary;
            var min1 = snake_width + boundary;
            var min2 = snake_width + boundary;
            var map_food_position = function(){
                console.log('('+min1+','+max1+') , ('+min2+', '+max2+')');
                food_position[0] = Math.floor((Math.random() * (max1 - min1) + min1)/min1) * min1;
                food_position[1] = Math.floor((Math.random() * (max2 - min2) + min2)/min2) * min2;
            }

            map_food_position();

            food.style.background = "#FF0000";
            food.style.display = "block";
            food.style.width = snake_width + "px";
            food.style.height = snake_width + "px";
            food.style.position = "absolute";
            food.style.left = food_position[0] + "px";
            food.style.top = food_position[1] + "px";
            food.style.border = "2px solid #000000";
            // Add food block to ground
            snake_ground.appendChild(food);

            // Game stats
            var game_stat_right = document.createElement('div');
            game_stat_right.style.display = 'block';
            game_stat_right.style.position = 'absolute';
            game_stat_right.style.right = '10px';
            game_stat_right.style.top = '10px';
            game_stat_right.style.font = 'bold 15px Monospace';
            game_stat_right.style.color = '#FFFFFF';

            var game_stat_left = document.createElement('div');
            game_stat_left.style.display = 'block';
            game_stat_left.style.position = 'absolute';
            game_stat_left.style.left = '10px';
            game_stat_left.style.top = '10px';
            game_stat_left.style.font = 'bold 15px Monospace';
            game_stat_left.style.color = '#FFFFFF';
            var game_player = document.createElement('div');

            game_player.innerHTML = 'Name: ' + 'Zlyden pisuhaty';
            game_player.style.display = 'block';
            game_player.style.position = 'absolute';
            game_player.style.left = '10px';
            game_player.style.top = '25px';
            game_player.style.font = 'bold 15px Monospace';
            game_player.style.color = '#FFFFFF';

            // Add game stats to ground
            snake_ground.appendChild(game_stat_right);
            snake_ground.appendChild(game_stat_left);
            snake_ground.appendChild(game_player);


            // Snake
            var snake = new Array();

            // Define first two blocks or elements of snake
            snake[0] = [Math.floor(ground_width/(2*snake_width)) * snake_width, Math.floor(ground_height/(2 * snake_width)) * snake_width];
            for(var i = 1; i <= snake_initial_length; i++){
                snake[i] = [snake[0][0] - i * snake_width, snake[0][1]];
            }

            // Set initial direction to right
            var snake_direction = DIR_RIGHT;

            // Variable to track game position
            var game_over = false;

            var key_code;

            var trigger_keydown_event =
                function(key_code){
                    // Create and dispatch an keyEvent to move snake's head towards snake_direction
                    if( window.KeyEvent ) {
                        var evObj = document.createEvent("KeyEvents");
                        evObj.initKeyEvent( "keydown", true, true, window, false, false, false, false, key_code, 0 );
                    } else {
                        var evObj = document.createEvent("UIEvents");
                        evObj.initUIEvent( "keydown", true, true, window, 1 );
                        evObj.keyCode = key_code;
                    }
                    document.dispatchEvent(evObj);
                }

            // Add nav keys for non-compatible browsers
            var button_up = document.createElement('button');
            button_up.style.position = 'absolute';
            button_up.style.display = 'block';
            button_up.style.width = '40px';
            button_up.style.height = '30px';
            button_up.innerHTML = '&uarr;';
            button_up.style.font = '15px Arial';
            button_up.style.top = (ground_height - 60) + 'px';
            button_up.style.left = (ground_width / 2 - 20) + 'px';
            var button_down = document.createElement('button');
            button_down.style.position = 'absolute';
            button_down.style.display = 'block';
            button_down.style.width = '40px';
            button_down.style.height = '30px';
            button_down.innerHTML = '&darr;';
            button_down.style.font = '15px Arial';
            button_down.style.top = (ground_height - 30) + 'px';
            button_down.style.left = (ground_width/2 - 20) + 'px';
            var button_left = document.createElement('button');
            button_left.style.position = 'absolute';
            button_left.style.display = 'block';
            button_left.style.width = '40px';
            button_left.style.height = '30px';
            button_left.innerHTML = '&larr;';
            button_left.style.font = '15px Arial';
            button_left.style.top = (ground_height - 30) + 'px';
            button_left.style.left = (ground_width / 2 - 60) + 'px';
            var button_right = document.createElement('button');
            button_right.style.position = 'absolute';
            button_right.style.display = 'block';
            button_right.style.width = '40px';
            button_right.style.height = '30px';
            button_right.innerHTML = '&rarr;';
            button_right.style.font = '15px Arial';
            button_right.style.top = (ground_height - 30) + 'px';
            button_right.style.left = (ground_width / 2 + 20) + 'px';
            // Handle button input

            button_left.onclick =
                function(){
                    trigger_keydown_event(KEY_LEFT);
                }
            button_right.onclick =
                function(){
                    trigger_keydown_event(KEY_RIGHT);
                }
            button_down.onclick =
                function(){
                    trigger_keydown_event(KEY_DOWN);
                }
            button_up.onclick =
                function(){
                    trigger_keydown_event(KEY_UP);
                }

            /*
                    body.appendChild(button_up);
                    body.appendChild(button_down);
                    body.appendChild(button_left);
                    body.appendChild(button_right);
             */

            // Loop for as long as needed
            var game_loop =
                function(){
                    if(!game_over){

                        trigger_keydown_event(snake_direction);
                        window.setTimeout(game_loop, snake_speed - speed_increment * game_level);
                    }else{
                        var gameover_dialog = document.createElement("div");
                        gameover_dialog.style.display = "block";
                        gameover_dialog.style.position = "absolute";
                        gameover_dialog.style.width = "200px";
                        gameover_dialog.style.height = "200px";
                        gameover_dialog.style.font = "bold 20px Arial";
                        gameover_dialog.style.color = "#FF0000";
                        gameover_dialog.style.left = (ground_width/2 - 100) + "px";
                        gameover_dialog.style.top = (ground_height/2 - 100) + "px";
                        gameover_dialog.style.textAlign = "center";
                        gameover_dialog.innerHTML = "GAME OVER!";
                        gameover_dialog_button = document.createElement("button");
                        gameover_dialog_button.style.display = "inline-block";
                        gameover_dialog_button.innerHTML = "Click To Play Again";
                        gameover_dialog_button.onclick = function(){
                            document.location.reload(false);
                        }
                        gameover_dialog.appendChild(gameover_dialog_button);
                        body.appendChild(gameover_dialog);
                        gameover_dialog_button.focus();
                    }
                }

            window.setTimeout(game_loop, snake_speed);


            // Experimental: Time wrap
            /*
                    document.onclick = function(e){
                            snake[0][0] = e.clientX;
                            snake[0][1] = e.clientY;
                    }
             */

            // Add keyDown event handler
            document.onkeydown = function(e){

                // Ignore keyDown events if game is over
                if(game_over){
                    return null;
                }

                // Prevent snake from moving in reverse direction
                if(snake_direction == DIR_UP && e.keyCode == KEY_DOWN){
                    return null;
                }
                if(snake_direction == DIR_RIGHT && e.keyCode == KEY_LEFT){
                    return null;
                }
                if(snake_direction == DIR_LEFT && e.keyCode == KEY_RIGHT){
                    return null;
                }
                if(snake_direction == DIR_DOWN && e.keyCode == KEY_UP){
                    return null;
                }

                // If one of the four navigation keys was pressed
                if(e.keyCode == KEY_UP || e.keyCode == KEY_LEFT || e.keyCode == KEY_DOWN || e.keyCode == KEY_RIGHT){

                    // Store position of last block, will be used when adding new tail block i.e. when snake's head eats food block
                    var last_x_position = snake[snake.length - 1][0];
                    var last_y_position = snake[snake.length - 1][1];

                    // Update every element to move to position of block ahead
                    for(var i = snake.length - 1; i > 0 ; i--){
                        snake[i][0] = snake[i-1][0];
                        snake[i][1] = snake[i-1][1];
                    }

                    // If UP key was pressed ( basically released )
                    if(e.keyCode == KEY_UP){
                        snake[0][1] -= snake_width;
                        snake_direction = DIR_UP;
                    }

                    // If LEFT key was pressed
                    if(e.keyCode == KEY_LEFT){
                        snake[0][0] -= snake_width;
                        snake_direction = DIR_LEFT;
                    }

                    // If DOWN key was pressed
                    if(e.keyCode == KEY_DOWN){
                        snake[0][1] += snake_width;
                        snake_direction = DIR_DOWN;
                    }

                    // If RIGHT key was pressed
                    if(e.keyCode == KEY_RIGHT){
                        snake[0][0] += snake_width;
                        snake_direction = DIR_RIGHT;
                    }

                    // Wrap the snake at right egde
                    if(snake[0][0] > ground_width){
                        snake[0][0] = 0;
                    }

                    // Wrap the snake at bottom edge
                    if(snake[0][1] > ground_height){
                        snake[0][1] = 0;
                    }

                    // Wrap the snake at left edge
                    if(snake[0][0] < 0){
                        snake[0][0] = ground_width;
                    }

                    // Wrap the snake at top edge
                    if(snake[0][1] < 0){
                        snake[0][1] = ground_height;
                    }

                    for(var i = 1; i < snake.length; i++){
                        if(snake[0][0] == snake[i][0] && snake[0][1] == snake[i][1]){
                            game_over = true;
                        }
                    }

                }

                // If snake's head has approached a food block
                if(Math.abs(snake[0][0] - food_position[0]) < snake_width && Math.abs(snake[0][1] - food_position[1]) < snake_width){

                    // Add a new tail block
                    snake[snake.length] = [last_x_position, last_y_position];

                    game_score++;
                    if(game_score != 0 && game_score%10 == 0 && game_level != 10){
                        game_level++;
                    }

                    // Play the audio
                    snake_audio.play();

                    // Find and update food block's new position
                    map_food_position();
                    food.style.left = food_position[0] + 'px';
                    food.style.top = food_position[1] + 'px';
                }

                game_stat_left.innerHTML = 'Score: ' + game_score;
                game_stat_right.innerHTML = 'Level: ' + (game_level + 1);


                // Add or modify snake blocks on each event
                for(var i = 0; i < snake.length; i++){
                    var snake_elem = document.getElementById("snake_"+i);
                    if(snake_elem == null){
                        snake_elem = document.createElement("div");
                        snake_elem.setAttribute("id", "snake_"+i);
                        snake_elem.style.position = "absolute";
                        snake_elem.style.display = "block";
                        snake_elem.style.width = snake_width + "px";
                        snake_elem.style.height = snake_width + "px";
                        snake_elem.style.background = "#00FF00";
                        snake_elem.style.border = "2px solid #000000";
                        snake_ground.appendChild(snake_elem);
                    }
                    snake_elem.style.left = snake[i][0] + "px";
                    snake_elem.style.top = snake[i][1] + "px";
                }

            };

        }
    </script>
</head>
<body>
<noscript>
    <h1>Please use a Javascript enabled web browser. Thank you.</h1>
</noscript>
</body>
</html>
<% } else {%>
<!DOCTYPE html>
<html>
<head>
    <title>Snake v1.0</title>
</head>
<body>
<form method="POST" action="sneak.jsp">
    Level: <select name="level">
    <%
        for (int $i = 1; $i <= 10; $i++) {
    %>
    <option value="<%= $i%>"><%= $i%></option>
    <%
        }
    %>
</select>
    Initial Length: <select name="ini_len">
    <%
        for (int $i = 1; $i <= 20; $i++) {
    %>
    <option value="<%= $i%>"><%= $i%></option>
    <%
        }
    %>
</select>
    <input type="submit" name="submit" value="Start Game" />
</form>
</body>
</html>
<%
    }
%>